package com.example.farm;

import com.example.farm.mappers.FarmMapper;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.FarmDetailDto;
import com.example.farm.repository.FarmDetailRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
class StartUpRunner implements CommandLineRunner {

//    private FarmRepository farmRepository;
//    private MetricRepository metricRepository;
    @Autowired
    private FarmMapper farmMapper;
    @Autowired
    private FarmDetailRepository farmDetailsRepository;

    @Override
    public void run (String... args) throws Exception {
//        if (farmRepository.count() == 0) {
//            saveFarmToDB();
//        }
//        if (metricRepository.count() == 0){
//            saveMetricToDB();
//        }

        if (farmDetailsRepository.count() == 0){
            List<String> fileNames = Arrays.asList("friman_metsola.csv", "Nooras_farm.csv", "ossi_farm.csv", "PartialTech.csv");
            for (String fileName : fileNames) {
                List<FarmDetailDto> dtos = readFileData(fileName);
                List<FarmDetail> details = farmMapper.fromDto(dtos);
                farmDetailsRepository.saveAll(details);
            }
        }

    }

    private List<FarmDetailDto> readFileData(String fileName) throws Exception {
        CSVReader reader=
                new CSVReaderBuilder(new FileReader("src/main/java/com/example/farm/FarmCsv/"+fileName)).
                        withSkipLines(1). // Skiping firstline as it is header
                        build();

        List<FarmDetailDto> csvObjectList=reader.readAll().stream().map(data-> {
            String location = data[0];
            String datetime = data[1];
            String sensorType = data[2];
            String value = data[3];

            BigDecimal metricVal = new BigDecimal(value);

            String[] input = location.split(" ");
            location = input[0];

            FarmDetailDto farmDto = new FarmDetailDto();
            farmDto.setFarm(location);
            farmDto.setMetric(sensorType);
            farmDto.setMetricValue(metricVal);
            farmDto.setDateTime(datetime);

            return farmDto;

        }).collect(Collectors.toList());

        return csvObjectList;
    }

//    private void saveFarmToDB() {
//        List<String> farms = Arrays.asList("Friman Metsola", "Noora's farm", "PartialTech Research Farm", "Organic Ossi's Impact That Lasts plantase");
//        for (var farmName : farms) {
//            Farm farm = new Farm();
//            farm.setFarmName(farmName);
//            farmRepository.save(farm);
//        }
//    }
//
//    private void saveMetricToDB() {
//        MetricType[] metrics = MetricType.values();
//        for (int i = 0; i < metrics.length; i++) {
//            MetricType metricStr = metrics[i];
//            Metric metric = new Metric();
//            metric.setType(metricStr);
//
//            metricRepository.save(metric);
//        }

    //}


}
