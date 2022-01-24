package com.example.farm.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="farm_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
@Builder


public class FarmDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "farm_id")
    private Farm farm;

    @ManyToOne
    @JoinColumn(nullable = false, name = "metric_id")
    private Metric metric;
    private BigDecimal metricValue; //TODO: convert to big decimal
    private LocalDateTime dateTime; //TODO: convert to localdatetime


}
