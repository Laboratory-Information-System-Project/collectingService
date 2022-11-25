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
    public List<String> collecting(@RequestBody Map<String, List<String>> barcodeList) {

        log.info("{}", barcodeList);

        List<String> barcodes = barcodeList.get("barcodeList");

        String result = collectingService.collect(barcodes);

        checkSuccessAndSendKafkaMessage("update success", result, "updateStatus", "C", barcodes);

        return barcodes;
    }

    @PutMapping("/collecting/canceldate")
    public List<String> cancelCollecting(@RequestBody Map<String, List<String>> barcodeListMap ) {
        List<String> barcodeList = barcodeListMap.get("barcodeList");
        String result = collectingService.removeCollectingInfo(barcodeList);

        checkSuccessAndSendKafkaMessage("채혈이 취소되었습니다.", result, "updateStatus", "B", barcodeList);

        return barcodeList;

    }

    private void checkSuccessAndSendKafkaMessage(String message, String result, String updateStatus, String status, List<String> barcodes) {
        if(Objects.equals(message, result)) {
            kafkaProducer.send(updateStatus, status, collectingService.getPrescribeCodeByBarcode(barcodes));
            barcodes.add(result);
        }
    }
}
