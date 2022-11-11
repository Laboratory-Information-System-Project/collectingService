package com.douzone.collectingservice.mapper;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectingMapper {
    String findBarcode();

    // barcode가 없거나 새로운 날짜 일때
    Integer insertNewBarcode(List<NewBarcodeDto> prescribeCodeList, String today);

    // barcode가 있고 당일 일때
    Integer insertBarcode(List<NewBarcodeDto> prescribeCodeList, String today);
}
