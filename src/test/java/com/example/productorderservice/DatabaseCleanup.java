package com.example.productorderservice;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseCleanup implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tabelNames;

    @Override
    public void afterPropertiesSet() {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tabelNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e-> {
                    String tableName = e.getJavaType().getAnnotation(Table.class).name();
                    return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()): tableName;
                })
                .collect(Collectors.toList());

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .toList();

        tabelNames.addAll(entityNames);
    }

    // Table Annotation이 붙어있는지 확인
    private boolean hasTableAnnotation(EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    // Entity Annotation이 붙어있는지 확인
    private boolean isEntity(final EntityType<?> e) {
       return null != e.getJavaType().getAnnotation(Entity.class);
    }

    // 테이블 초기화
    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate(); // 외래키 제약조건 무시

        for (final String tableName : tabelNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate(); // 테이블 초기화
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate(); // id 초기화
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
