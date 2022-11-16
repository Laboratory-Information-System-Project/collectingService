package com.douzone.collectingservice.service.receiptservice;

import com.douzone.collectingservice.domain.receiptdomain.ReceiptDomainDTO;

import java.util.List;

public interface ReceiptService {
    List<ReceiptDomainDTO> collectData(String barcode);
}
