package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.service.CollectingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class CollectingController {
    private final CollectingService collectingService;

    @PostMapping("/new-barcode")
    @ResponseStatus(HttpStatus.CREATED)
    public String newBarcode(List<NewBarcodeDto> prescribeCodeList){
        return collectingService.createBarcode(prescribeCodeList);
    }
}