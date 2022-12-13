package com.douzone.collectingservice.service.barcode;

import java.util.List;
import java.util.Map;

public interface BarcodeService {
    String createBarcode(Map<String, List<Object>> prescribeCodeList);

    List<String> getBarcodeList(List<String> prescribeCodeList);

    String removeBarcode(Map<String, List<String>> barcodeList);

    List<Map<Object, Object>> getAll(List<String> prescribeCodeList);
}
