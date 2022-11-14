package com.douzone.collectingservice.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectingMapper {
    Integer findCollectedBarcode(List<String> barcodeList);

    Integer updateCollectingData(List<String> barcodeList);
}
