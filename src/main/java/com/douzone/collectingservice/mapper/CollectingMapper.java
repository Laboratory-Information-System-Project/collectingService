package com.douzone.collectingservice.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectingMapper {
    List<String> findCollectedPrescribeCode(List<String> barcodeList);

    Integer updateCollectingData(List<String> barcodeList, String userId);

    List<String> findPrescribeCodeByBarcode(List<String> barcodeList);

    Integer deleteCollectingData(List<String> barcodeList, String userId);

    void updateCollectingForDelete(List<String> prescribeCodeList);

    void updateCancelInspection(Object prescribeCode, String status);
}
