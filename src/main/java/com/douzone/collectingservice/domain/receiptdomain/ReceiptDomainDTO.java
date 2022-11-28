package com.douzone.collectingservice.domain.receiptdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDomainDTO {
    private String barcode;
    private Date collecting_dt;
    private String collector_id;
    private Long prescribe_code;

    // 스키마 넘어가서 처방 테이블 > 검사종류 마스터로 가야됨
//    private String inspection_code;
//    private String order_code;
//    private String vessel_code;
}
