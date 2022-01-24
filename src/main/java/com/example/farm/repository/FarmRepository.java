package com.example.farm.repository;

import com.example.farm.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm,Long> {
    @Override
    List<Farm> findAll ();
    Farm findByFarmName(String farmName);


}
