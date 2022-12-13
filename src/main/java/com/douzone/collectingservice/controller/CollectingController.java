package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.service.collecting.CollectingService;
import com.douzone.collectingservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/collecting")
    public String collecting(@RequestBody Map<String, List<String>> prescribeCodeList) {

        log.info("{}", prescribeCodeList);

        List<String> prescribeCodes = prescribeCodeList.get("prescribeCodeList");

        String result = collectingService.collect(prescribeCodeList);

        checkSuccessAndSendKafkaMessage("update success", result, "updateStatus", "C", prescribeCodes);

        return result;
    }

    @PutMapping("/collecting/canceldate")
    public String cancelCollecting(@RequestBody Map<String, List<String>> barcodeListMap ) {
        List<String> prescribeCodeList = barcodeListMap.get("prescribeCodeList");
        String result = collectingService.removeCollectingInfo(barcodeListMap);

        checkSuccessAndSendKafkaMessage("채혈이 취소되었습니다.", result, "updateStatus", "B", prescribeCodeList);

        return result;

    }

    private void checkSuccessAndSendKafkaMessage(String message, String result, String updateStatus, String status, List<String> prescribeCodes) {
        if(Objects.equals(message, result)) {
            kafkaProducer.send(updateStatus, status, prescribeCodes);
            prescribeCodes.add(result);
        }
    }
}
