package com.douzone.collectingservice.mapper;


import com.douzone.collectingservice.domain.UnsuitableSampleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SampleMapper {
    void insertUnsuitableSampleInfo(Map<String, String> unsuitInfo);
    UnsuitableSampleDTO findUnsuitableSample(Map<String, String> unsuitInfo);
}
