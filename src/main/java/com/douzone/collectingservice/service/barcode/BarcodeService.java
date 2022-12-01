package com.douzone.collectingservice.service.barcode;

import com.douzone.collectingservice.domain.NewBarcodeDto;

import java.util.List;
import java.util.Map;

public interface BarcodeService {
    String createBarcode(NewBarcodeDto prescribeCodeList);

    List<String> getBarcodeList(NewBarcodeDto prescribeCodeList);

    String removeBarcode(List<String> barcodeList);

    List<Map<Object, Object>> getAll(NewBarcodeDto prescribeCodeList);
}
