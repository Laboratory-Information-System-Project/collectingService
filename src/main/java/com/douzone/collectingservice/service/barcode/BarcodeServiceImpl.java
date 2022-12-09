package com.douzone.collectingservice.service.barcode;

import com.douzone.collectingservice.domain.NewBarcodeDto;
import com.douzone.collectingservice.mapper.BarcodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

        // barcode가 없을때
        if (Objects.isNull(barcode) || Objects.equals(false, today.matches(barcode.substring(0,6)))) {
            // 시퀀스를 초기화 하고 바코드를 새로 생성
            barcodeMapper.initBarcode();
            return insertNewBarcode(prescribeCodeList, today);
        }

        // barcode가 있고 당일 일때
        return insertNewBarcode(prescribeCodeList, today);

    }

    @Override
    public List<String> getBarcodeList(NewBarcodeDto prescribeCodeList) {

        return barcodeMapper.findBarcodeByPrescribeCode(prescribeCodeList.getPrescribeCodeList());
    }

    @Override
    public String removeBarcode(List<String> barcodeList) {
        Integer result = barcodeMapper.deleteBarcode(barcodeList);

        if(result == barcodeList.size()){
            return "선택하신 바코드 발급이 취소되었습니다";
        }
        return "바코드 발급 취소가 실패하였습니다.";
    }

    @Override
    public List<Map<Object, Object>> getAll(NewBarcodeDto prescribeCodeList) {
        return barcodeMapper.findAllByPrescribeCodeForKafka(prescribeCodeList);
    }

    private String insertNewBarcode(NewBarcodeDto prescribeCodeList, String today) {
        Integer result = barcodeMapper.insertNewBarcode(prescribeCodeList.getPrescribeCodeList(), today);

        if (result==1) {
            return "create barcode successfully!";
        } else {
            return "failed create barcode!";
        }
    }
}