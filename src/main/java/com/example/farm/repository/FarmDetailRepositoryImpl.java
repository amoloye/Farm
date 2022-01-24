package com.example.farm.repository;

import com.example.farm.model.FarmDetail;
import com.example.farm.model.MetricType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FarmDetailRepositoryImpl {

    private final EntityManager entityManager;

    public FarmDetailRepositoryImpl (final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<FarmDetail> findByMonthAndYear(String month, String year){
        // select * from farmdetails where month(date_time) =:month and year(date_time) =:year
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FarmDetail> criteriaQuery = criteriaBuilder.createQuery(FarmDetail.class);
        Root<FarmDetail> root = criteriaQuery.from(FarmDetail.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(criteriaBuilder
                        .function("MONTH", Integer.class, root.get("dateTime")), Integer.valueOf(month)));
        predicates.add(criteriaBuilder.equal(criteriaBuilder
                .function("YEAR", Integer.class, root.get("dateTime")), Integer.valueOf(year)));

        Predicate whereClause = criteriaBuilder.and(predicates.get(0), predicates.get(1));
        CriteriaQuery<FarmDetail> query = criteriaQuery.select(root).where(whereClause);

        return entityManager.createQuery(query).getResultList();
    }


    public List<FarmDetail>findAllByMetricType( MetricType type){
        // select * from farmdetails where metricdata =:type

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FarmDetail> criteriaQuery = criteriaBuilder.createQuery(FarmDetail.class);
        Root<FarmDetail> root = criteriaQuery.from(FarmDetail.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("metric"), type));

        Predicate whereClause = criteriaBuilder.and(predicates.get(0));
        CriteriaQuery<FarmDetail> query = criteriaQuery.select(root).where(whereClause);

        return entityManager.createQuery(query).getResultList();
    }
}
