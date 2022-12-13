package com.douzone.collectingservice.service.collecting;

import com.douzone.collectingservice.mapper.CollectingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectingServiceImpl implements CollectingService{

    private final CollectingMapper collectingMapper;

    @Override
    @Transactional
    public String collect(Map<String, List<String>> prescribeCodeList) {

        if(!collectingMapper.findCollectedPrescribeCode(prescribeCodeList.get("prescribeCodeList")).isEmpty()){
            return "collecting data already exists";
        }

        int result = collectingMapper.updateCollectingData(prescribeCodeList.get("prescribeCodeList"), prescribeCodeList.get("userId").get(0));
        log.info("{}", prescribeCodeList.get("prescribeCodeList").size());
        log.info("{}", result);

        if( result == prescribeCodeList.get("prescribeCodeList").size()){

            return "update success";
        }

        return "update fail";
    }

    // @Override
    // public List<String> getPrescribeCodeByBarcode(List<String> prescribeCodes) {
    //     return collectingMapper.findPrescribeCodeByBarcode(prescribeCodes);
    // }

    @Override
    @Transactional
    public String removeCollectingInfo(Map<String, List<String>> prescribeCodeList) {
        Integer result = collectingMapper.deleteCollectingData(prescribeCodeList.get("prescribeCodeList"), prescribeCodeList.get("userId").get(0));

        if(result == prescribeCodeList.get("prescribeCodeList").size()){
            return "채혈이 취소되었습니다.";
            }

        return "채혈 취소에 실패하였습니다.";
    }


}
