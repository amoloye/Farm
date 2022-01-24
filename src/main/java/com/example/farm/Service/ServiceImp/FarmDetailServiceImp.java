package com.example.farm.Service.ServiceImp;

import com.example.farm.Service.FarmDetailService;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.Metric;
import com.example.farm.model.MetricType;
import com.example.farm.repository.FarmDetailRepository;
import com.example.farm.repository.MetricRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FarmDetailServiceImp implements FarmDetailService {
    FarmDetailRepository farmDetailRepository;
    MetricRepository metricRepository;


    @Override
    public List<FarmDetail> fetchStatisticsFromFarmDetailsData () {
        return null;
    }

    @Override
    public List<Integer> getMetricValue (List<Integer> value) {
        return null;
    }

    @Override
    public List<FarmDetail> fetchFarmDetailsData (LocalDateTime dateTime, MetricType type) {

        List<FarmDetail> farmDetails = Collections.emptyList();
        //if (dateTime==null && type == null) {
            //farmDetails = farmDetailRepository.findAll();
        //}

        try {
             Optional<Metric> opType= metricRepository.findByType(type);
             type = opType.get().getType();

              String month =String.valueOf(dateTime.getMonth());


              farmDetails = farmDetailRepository.findByMonthAndMetric(month,type);
        }
        catch (Exception e){
            System.out.println("Not acceptable, input in the empty form");
        }

        return  farmDetails;
    }







}
