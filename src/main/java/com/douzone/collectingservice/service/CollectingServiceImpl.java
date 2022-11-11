package com.douzone.collectingservice.service;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.mapper.CollectingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectingServiceImpl implements CollectingService {
    private final CollectingMapper collectingMapper;

    @Override
    public String createBarcode(List<NewBarcodeDto> prescribeCodeList) {
        // 가장 최근 생성된 바코드를 찾아서 시퀀스와 날짜를 확인하고 새로운 바코드를 생성
        String barcode = collectingMapper.findBarcode();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        Integer result = 0;
        String dayForCompare = barcode.substring(6);

        // barcode가 없거나 새로운 날짜 일때
        if (barcode.isEmpty() || !today.matches(dayForCompare)) {
            // 시퀀스를 초기화 하고 바코드를 새로 생성
            result = collectingMapper.insertNewBarcode(prescribeCodeList, today);

            return validateInsert(prescribeCodeList, result);
        }

        // barcode가 있고 당일 일때
        result = collectingMapper.insertBarcode(prescribeCodeList, today);

        return validateInsert(prescribeCodeList, result);

    }

    private static String validateInsert(List<NewBarcodeDto> prescribeCodeList, Integer result) {
        if (prescribeCodeList.size() == result) {
            return "create barcode successfully!";
        } else {
            return "failed create barcode!";
        }
    }
}
