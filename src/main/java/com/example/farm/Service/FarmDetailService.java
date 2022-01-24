package com.example.farm.Service;

import com.example.farm.model.FarmDetail;
import com.example.farm.model.FarmDetailDto;
import com.example.farm.model.MetricType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FarmDetailService {
//     static List<FarmDetail> fetchStatisticsFromFarmDetailsData (LocalDateTime dateTime, MetricType type, BigDecimal metricValue) {
//          return null;
//     }

     List<FarmDetail> fetchStatisticsFromFarmDetailsData ();
     List<Integer> getMetricValue(List<Integer>value);

     List<FarmDetailDto> fetchFarmDetailsByMetric (String type);
     List<FarmDetailDto> fetchDataByMonthAndYear (String dateTime);
}
