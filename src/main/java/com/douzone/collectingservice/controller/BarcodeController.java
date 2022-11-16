package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.service.BarcodeService;
import com.douzone.collectingservice.service.collecting.CollectingService;
import com.douzone.collectingservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final CollectingService collectingService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/barcode")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> newBarcode(@RequestBody NewBarcodeDto prescribeCodeList) {
        String result = barcodeService.createBarcode(prescribeCodeList);
        List<String> barcode = new ArrayList<>();
        barcode.add(result);

        kafkaProducer.send("updateStatus","B", prescribeCodeList.getPrescribeList());

        barcode.addAll(barcodeService.getBarcodeList(prescribeCodeList));

        return barcode;
    }

    // FIXME url마음에 안든다...
    @PostMapping("/barcode/canceldate")
    public List<String> cancelBarcode(@RequestBody Map<String, List<String>> barcodeListMap){
        List<String> barcodeList = barcodeListMap.get("barcodeList");
        String result = barcodeService.removeBarcode(barcodeList);

        if(Objects.equals(result, "선택하신 바코드 발급이 취소되었습니다")){
            kafkaProducer.send("updateStatus","X", collectingService.getPrescribeCodeByBarcode(barcodeList));
        }

        barcodeList.add(result);

        return barcodeList;
    }
}