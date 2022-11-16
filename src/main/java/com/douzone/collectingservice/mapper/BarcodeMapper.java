package com.douzone.collectingservice.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BarcodeMapper {
    String findBarcode();

    // barcode가 없거나 새로운 날짜 일때
    Integer insertNewBarcode(List<String> prescribeCodeList, String today);

    // barcode가 있고 당일 일때
    // Integer insertBarcode(List<Long> prescribeCodeList, String today);

    void initBarcode();

    List<String> findAllByPrescribeCode(List<String> prescribeList);

    Integer deleteBarcode(List<String> barcodeList);

}
