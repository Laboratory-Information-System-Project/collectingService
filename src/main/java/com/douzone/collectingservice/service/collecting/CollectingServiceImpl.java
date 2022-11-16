package com.douzone.collectingservice.service.collecting;

import com.douzone.collectingservice.mapper.CollectingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectingServiceImpl implements CollectingService{

    private final CollectingMapper collectingMapper;

    @Override
    @Transactional
    public String collect(List<String> barcodeList) {

        if(!collectingMapper.findCollectedBarcode(barcodeList).isEmpty()){
            return "collecting data already exists";
        }

        if(collectingMapper.updateCollectingData(barcodeList) == barcodeList.size()){

            return "update success";
        }

        return "update fail";
    }

    @Override
    public List<String> getPrescribeCodeByBarcode(List<String> barcodeList) {
        return collectingMapper.findPrescribeCodeByBarcode(barcodeList);
    }

    @Override
    @Transactional
    public String removeCollectingInfo(List<String> barcodeList) {
        Integer result = collectingMapper.deleteCollectingData(barcodeList);

        if(result == barcodeList.size()){
            return "채혈이 취소되었습니다.";
            }

        return "채혈 취소에 실패하였습니다.";
    }


}