package com.example.farm.repository;

import com.example.farm.model.Metric;
import com.example.farm.model.MetricType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricRepository extends JpaRepository<Metric,Long> {
    Optional<Metric> findByType(MetricType type);
}
