package com.douzone.collectingservice.service;

import com.douzone.collectingservice.domain.NewBarcodeDto;

import java.util.List;

public interface CollectingService {
    String createBarcode(List<NewBarcodeDto> prescribeCodeList);
}
