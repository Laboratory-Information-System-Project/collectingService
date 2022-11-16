package com.douzone.collectingservice.service;

import com.douzone.collectingservice.domain.NewBarcodeDto;

import java.util.List;

public interface BarcodeService {
    String createBarcode(NewBarcodeDto prescribeCodeList);

    List<String> getBarcodeList(NewBarcodeDto prescribeCodeList);

    String removeBarcode(List<String> barcodeList);
}
