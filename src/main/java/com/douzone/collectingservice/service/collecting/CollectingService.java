package com.douzone.collectingservice.service.collecting;

import java.util.List;

public interface CollectingService {
    String collect(List<String> barcodeList);
    List<String> getPrescribeCodeByBarcode(List<String> barcodeList);

    String removeCollectingInfo(List<String> barcodeList);
}
