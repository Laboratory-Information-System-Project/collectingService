package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.domain.receiptdomain.ReceiptDomainDTO;
import com.douzone.collectingservice.service.receiptservice.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collecting-service")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService service;

    @GetMapping("/collect/{barcode}")
    public List<ReceiptDomainDTO> collectData(@PathVariable String barcode){
        return service.collectData(barcode);
    }
}
