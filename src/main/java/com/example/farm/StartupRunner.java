package com.example.farm;

import com.example.farm.model.Farm;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.Metric;
import com.example.farm.model.MetricType;
import com.example.farm.repository.FarmDetailRepository;
import com.example.farm.repository.FarmRepository;
import com.example.farm.repository.MetricRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
class StartUpRunner implements CommandLineRunner {

    private FarmRepository farmRepository;
    private MetricRepository metricRepository;
    private FarmDetailRepository farmDetailsRepository;

    @Override
    public void run (String... args) throws Exception {
        if (farmRepository.count() == 0) {
            saveFarmToDB();
        }
        if (metricRepository.count() == 0){
            saveMetricToDB();
        }

        if (farmDetailsRepository.count() == 0){
            List<String> fileNames = Arrays.asList("friman_metsola.csv", "Nooras_farm.csv", "ossi_farm.csv", "PartialTech.csv");
            for (String fileName : fileNames) {
                List<FarmDetail> details = readFileData(fileName);
                farmDetailsRepository.saveAll(details);
            }
        }

    }

    private List<FarmDetail> readFileData(String fileName) throws Exception {
        CSVReader reader=
                new CSVReaderBuilder(new FileReader("src/main/java/com/example/farm/FarmCsv"+fileName)).
                        withSkipLines(1). // Skiping firstline as it is header
                        build();

        List<FarmDetail> csvObjectList=reader.readAll().stream().map(data-> {
            String location = data[0];
            LocalDateTime datetime = LocalDateTime.parse(data[1].toString());
            String sensorType = data[2];
            String value = data[3];


            Farm farm = farmRepository.findByFarmName(location);
            Metric metric = metricRepository.findByType(MetricType.valueOf(sensorType)).get();

            FarmDetail farmDetail = new FarmDetail();
            farmDetail.setFarm(farm);
            farmDetail.setMetric(metric);
            farmDetail.setMetricValue(new BigDecimal(value));
            farmDetail.setDateTime(datetime);

            return farmDetail;

        }).collect(Collectors.toList());

        return csvObjectList;
    }

    private void saveFarmToDB() {
        List<String> farms = Arrays.asList("Friman Metsola", "Noora's farm", "PartialTech Research Farm", "Organic Ossi's Impact That Lasts plantase");
        for (var farmName : farms) {
            Farm farm = new Farm();
            farm.setFarmName(farmName);
            farmRepository.save(farm);
        }
    }

    private void saveMetricToDB() {
        MetricType[] metrics = MetricType.values();
        for (int i = 0; i < metrics.length; i++) {
            MetricType metricStr = metrics[i];
            Metric metric = new Metric();
            metric.setType(metricStr);

            metricRepository.save(metric);
        }

    }


}
