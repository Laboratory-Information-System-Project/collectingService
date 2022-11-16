package com.douzone.collectingservice.mapper.receiptMapper;


import com.douzone.collectingservice.domain.receiptdomain.ReceiptDomainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReceiptMapper {
    List<ReceiptDomainDTO> collectData(String barcode);
    List<String> test();
}
