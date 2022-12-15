package com.douzone.collectingservice.service.kafka;

import com.douzone.collectingservice.mapper.CollectingMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    // FIXME: repository -> mapper
    private final CollectingMapper collectingMapper;
    //
    // FIXME: topics modify, method customizing
    @KafkaListener(topics = "updateCancellation")
    public void updateCollecting(String kafkaMessage){
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {});
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

        collectingMapper.updateCancelInspection(map.get("prescribeCode"), map.get("status").toString());
    }
}