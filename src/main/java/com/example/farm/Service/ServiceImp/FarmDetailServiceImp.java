package com.example.farm.Service.ServiceImp;

import com.example.farm.Service.FarmDetailService;
import com.example.farm.mappers.FarmMapper;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.FarmDetailDto;
import com.example.farm.model.MetricType;
import com.example.farm.repository.FarmDetailRepository;
import com.example.farm.repository.FarmDetailRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FarmDetailServiceImp implements FarmDetailService {

    @Autowired
    private FarmDetailRepository farmDetailRepository;

    @Autowired
    private FarmMapper farmMapper;

    @Autowired
    private FarmDetailRepositoryImpl farmDetailRepositoryImpl;


    @Override
    public List<FarmDetail> fetchStatisticsFromFarmDetailsData () {
        return null;
    }

    @Override
    public List<Integer> getMetricValue (List<Integer> value) {
        return null;
    }

    @Override
    public List<FarmDetailDto> fetchFarmDetailsByMetric (String type) {

        List<FarmDetail> details = farmDetailRepositoryImpl.findAllByMetricType(MetricType.valueOf(type));

        return farmMapper.toDto(details);
    }

    @Override
    public List<FarmDetailDto> fetchDataByMonthAndYear (String dateTime) {

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        List<FarmDetail> details = farmDetailRepositoryImpl.findByMonthAndYear(String.valueOf(localDateTime.getMonth()),
                String.valueOf(localDateTime.getYear()));

        return farmMapper.toDto(details);
    }






}
