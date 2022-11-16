package com.douzone.collectingservice.service.receiptservice;

import com.douzone.collectingservice.mapper.receiptMapper.ReceiptMapper;
import com.douzone.collectingservice.domain.receiptdomain.ReceiptDomainDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService{
    private final ReceiptMapper receiptMapper;

    @Override
    public List<ReceiptDomainDTO> collectData(String barcode) {
        receiptMapper.test();
        return receiptMapper.collectData(barcode);

    }
}
