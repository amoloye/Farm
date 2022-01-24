package com.example.farm.model;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
public enum MetricType {
    @Min(-50)
    @Max(100)
    temperature,

    @Min(0)
    @Max(500)
    rainFall,

    @Min(0)
    @Max(14)
    pH

}
