package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.service.barcode.BarcodeService;
import com.douzone.collectingservice.service.collecting.CollectingService;
import com.douzone.collectingservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final CollectingService collectingService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/barcode")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> newBarcode (@RequestBody Map<String, List<Object>> prescribeInfoList) {
        String result = barcodeService.createBarcode(prescribeInfoList);
        List<String> prescribeCodes = prescribeInfoList.get("prescribeCodeList").stream().map(item-> item.toString()).collect(Collectors.toList());
        List<String> barcode = new ArrayList<>();
        barcode.add(result);

        kafkaProducer.send("updateStatus","B", prescribeCodes);

        barcode.addAll(barcodeService.getBarcodeList(prescribeCodes));

        List<Map<Object, Object>> prescribe = barcodeService.getAll(prescribeCodes);

        return barcode;
    }

    @PutMapping("/barcode")
    public String cancelBarcode(@RequestBody Map<String, List<String>> barcodeListMap){
        List<String> prescribeCodeList = barcodeListMap.get("prescribeCodeList");
        String result = barcodeService.removeBarcode(barcodeListMap);

        if(Objects.equals(result, "선택하신 바코드 발급이 취소되었습니다")){
            kafkaProducer.send("updateStatus","X", prescribeCodeList);
        }

        prescribeCodeList.add(result);

        return result;
    }
}