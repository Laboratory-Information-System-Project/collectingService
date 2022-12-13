package com.douzone.collectingservice.service.collecting;

import java.util.List;
import java.util.Map;

public interface CollectingService {
    String collect(Map<String, List<String>> barcodeList);
    // List<String> getPrescribeCodeByBarcode(List<String> barcodeList);

    String removeCollectingInfo(Map<String, List<String>> barcodeList);
}
