package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.service.BarcodeService;
import com.douzone.collectingservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/barcode")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> newBarcode(@RequestBody NewBarcodeDto prescribeCodeList){
        String result = barcodeService.createBarcode(prescribeCodeList);
        List<String> barcode = new ArrayList<>();
        barcode.add(result);

        kafkaProducer.send("updateStatus","B", prescribeCodeList.getPrescribeList());

        barcode.addAll(barcodeService.getBarcodeList(prescribeCodeList));

        return barcode;
    }

    // @PostMapping("/collecting")
    // public String collect(CollectDto collectDto){
    //
    // }
}