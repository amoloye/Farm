package com.example.farm.mappers;

import com.example.farm.model.Farm;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.FarmDetailDto;
import com.example.farm.model.MetricType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FarmMapper {

    public FarmDetail fromDto(FarmDetailDto farmDetailDto){
        if (farmDetailDto == null){
            return null;
        }
        FarmDetail farmDetail = new FarmDetail();

        farmDetail.setFarm(Farm.valueOf(farmDetailDto.getFarm()));
        farmDetail.setId(farmDetailDto.getId());
        farmDetail.setMetric(MetricType.valueOf(farmDetailDto.getMetric()));
        farmDetail.setDateTime(LocalDateTime.parse(farmDetailDto.getDateTime()));
        farmDetail.setMetricValue(farmDetailDto.getMetricValue());

        return farmDetail;
    }

    public FarmDetailDto toDto(FarmDetail farmDetail){
        if (farmDetail == null){
            return null;
        }
        FarmDetailDto farmDetailDto = new FarmDetailDto();

        farmDetailDto.setFarm(String.valueOf(farmDetail.getFarm()));
        farmDetailDto.setId(farmDetail.getId());
        farmDetailDto.setMetric(String.valueOf(farmDetail.getMetric()));
        farmDetailDto.setDateTime(String.valueOf(farmDetail.getDateTime()));
        farmDetailDto.setMetricValue(farmDetail.getMetricValue());

        return farmDetailDto;
    }

    public List<FarmDetailDto> toDto(List<FarmDetail> farmDetailList){
        if (farmDetailList == null){
            return null;
        }
        List<FarmDetailDto> farmDetailDtos = new ArrayList<>(farmDetailList.size());

        for (FarmDetail detail : farmDetailList) {
            farmDetailDtos.add(toDto(detail));
        }

        return farmDetailDtos;
    }

    public List<FarmDetail> fromDto(List<FarmDetailDto> farmDetailDtos){
        if (farmDetailDtos == null){
            return null;
        }
        List<FarmDetail> farmDetails = new ArrayList<>(farmDetailDtos.size());

        for (FarmDetailDto dtoDetail : farmDetailDtos) {
            farmDetails.add(fromDto(dtoDetail));
        }

        return farmDetails;
    }

}
