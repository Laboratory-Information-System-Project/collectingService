package com.douzone.collectingservice.service;

import com.douzone.collectingservice.domain.UnsuitableSampleDTO;
import com.douzone.collectingservice.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService{
    private final SampleMapper mapper;
    @Override
    public void insertUnsuitableSample(List<Map<String, String>> unsuitInfo) {
        for(Map<String, String> item : unsuitInfo
             ) {
            //부적합 검체 사유 체크
            UnsuitableSampleDTO result = mapper.findUnsuitableSample(item);
            if(result == null){
                mapper.insertUnsuitableSampleInfo(item);
            }



        }
    }


}
