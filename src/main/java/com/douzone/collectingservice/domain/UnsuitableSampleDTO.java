package com.douzone.collectingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsuitableSampleDTO {
    private String barcode;
    private String unsuitableReasonCode;
    private String unsuitableReasonDetail;
    private String notifiedUserId;
    private String notificatorId;
}
