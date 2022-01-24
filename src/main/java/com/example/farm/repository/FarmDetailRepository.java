package com.example.farm.repository;

import com.example.farm.model.FarmDetail;
import com.example.farm.model.MetricType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmDetailRepository extends JpaRepository<FarmDetail, Long> {



}
