package com.example.farm;

import com.example.farm.mappers.FarmMapper;
import com.example.farm.model.FarmDetail;
import com.example.farm.model.FarmDetailDto;
import com.example.farm.model.MetricType;
import com.example.farm.repository.FarmDetailRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StartUpRunner implements CommandLineRunner {

//    private FarmRepository farmRepository;
//    private MetricRepository metricRepository;
    @Autowired
    private FarmMapper farmMapper;
    @Autowired
    private FarmDetailRepository farmDetailsRepository;

    @Override
    public void run (String... args) throws Exception {

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

        Set<String> sensors = new HashSet<>();
        sensors.add(MetricType.temperature.toString());
        sensors.add(MetricType.pH.toString());
        sensors.add(MetricType.rainFall.toString());

        CSVReader reader=
                new CSVReaderBuilder(new FileReader("src/main/java/com/example/farm/FarmCsv/"+fileName)).
                        withSkipLines(1). // Skiping firstline as it is header
                        build();

        List<FarmDetailDto> csvObjectList=reader.readAll().stream().map(data-> {
            String location = data[0];
            String datetime = data[1];
            String sensorType = (sensors.contains(data[2])) ? data[2] : MetricType.Invalid.toString();
            String value = data[3];

            BigDecimal metricVal = (value.isEmpty() || value.equalsIgnoreCase("null")) ?
                    new BigDecimal(0) : new BigDecimal(value);

            String[] input = location.split(" ");
            location = input[0]+""+input[1];

            if(location.contains("\'")){
                String[] input2 = location.split("\'");
                location = input2[0]+""+input2[1];
            }

            FarmDetailDto farmDto = new FarmDetailDto();
            farmDto.setFarm(location);
            farmDto.setMetric(sensorType);
            farmDto.setMetricValue(metricVal);
            farmDto.setDateTime(datetime);

            return farmDto;

        }).collect(Collectors.toList());

        return csvObjectList;
    }


}
