package com.example.farm.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FarmDetailDto {
        private Long id;
        private String farm;

        private String metric;

        private BigDecimal metricValue;
        private String dateTime;

}
