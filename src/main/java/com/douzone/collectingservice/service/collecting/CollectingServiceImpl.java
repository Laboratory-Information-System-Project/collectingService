package com.douzone.collectingservice.service.collecting;

import com.douzone.collectingservice.mapper.CollectingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectingServiceImpl implements CollectingService{

    private final CollectingMapper collectingMapper;

    @Override
    @Transactional
    public String collect(List<String> barcodeList) {
        Integer result = collectingMapper.findCollectedBarcode(barcodeList);

        if(result > 0){
            return "collecting data already exists";
        }

        Integer resultNo = collectingMapper.updateCollectingData(barcodeList);

        if(resultNo == barcodeList.size()){
            return "update success";
        }

        return "update fail";
    }
}
