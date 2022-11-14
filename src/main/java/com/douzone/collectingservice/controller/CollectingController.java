package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.service.collecting.CollectingService;
import com.douzone.collectingservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class CollectingController {
    private final CollectingService collectingService;
    private final KafkaProducer kafkaProducer;
    @PostMapping("/collecting")
    public String collecting(@RequestBody Map<String, List<String>> barcodeList){

        log.info("{}", barcodeList);

        String result = collectingService.collect(barcodeList.get("barcodeList"));

        if(Objects.equals("update success",result)) {
            kafkaProducer.send("updateStatus", "C", barcodeList.get("barcodeList"));
        }

        return result;
    }

}
