package com.douzone.collectingservice.service;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.mapper.BarcodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements BarcodeService {
    private final BarcodeMapper barcodeMapper;

    @Override
    public String createBarcode(NewBarcodeDto prescribeCodeList) {
        // 가장 최근 생성된 바코드를 찾아서 시퀀스와 날짜를 확인하고 새로운 바코드를 생성
        String barcode = barcodeMapper.findBarcode();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        //TODO 리팩토링(바코드 없을때랑 새로운 날짜일때 완벽하게 같은 작업을 하는데 나눠야 하는게 마음에 안듬)

        // barcode가 없을때
        if (Objects.isNull(barcode)) {
            // 시퀀스를 초기화 하고 바코드를 새로 생성
            barcodeMapper.initBarcode();
            return insertNewBarcode(prescribeCodeList, today);
        }

        String dayForCompare = barcode.substring(0,6);
        // 새로운 날짜일때
        if(!today.matches(dayForCompare)){
            barcodeMapper.initBarcode();
            return insertNewBarcode(prescribeCodeList, today);
        }

        // barcode가 있고 당일 일때
        return insertNewBarcode(prescribeCodeList, today);

    }

    @Override
    public List<String> getBarcodeList(NewBarcodeDto prescribeCodeList) {
        return null;
    }

    private String insertNewBarcode(NewBarcodeDto prescribeCodeList, String today) {
        Integer result = barcodeMapper.insertNewBarcode(prescribeCodeList.getPrescribeList(), today);

        if (prescribeCodeList.getPrescribeList().size() == result) {
            return "create barcode successfully!";
        } else {
            return "failed create barcode!";
        }
    }
}
