package com.example.farm.repository;

import com.example.farm.model.Farm;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.MetricType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest

class FarmDetailRepositoryTest {

    @Autowired
    private FarmDetailRepository farmDetailRepository;

//    @Test
//    public FarmDetail createFarmDetails(){
//        FarmDetail farmDetail = FarmDetail.builder()
//                .id(1L)
//                .farm(new Farm(1,"Kellogg_farm"))
//                .metric(new Metric(1,MetricType.pH))
//                .metricValue(BigDecimal.valueOf(12.0))
//                .dateTime(LocalDateTime.parse("2019-01-01T23:10:20.551Z"))
//                .build();
//
//        return  farmDetailRepository.save(farmDetail);
//
//    }
    @Test
    public List<FarmDetail> farmList(){
        return farmDetailRepository.findAll();

    }
}