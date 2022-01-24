package com.example.farm.repository;

import com.example.farm.model.FarmDetail;
import com.example.farm.model.MetricType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FarmDetailRepository extends JpaRepository<FarmDetail, Long> {



    List<FarmDetail>findByMonthAndMetric(String month, MetricType metricValue);


    List<FarmDetail>findAllById(String dateTime, MetricType type, BigDecimal metricValue);



}
