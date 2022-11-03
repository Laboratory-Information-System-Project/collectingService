package com.douzone.collectingservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    // FIXME: repository -> mapper
    // private final CatalogRepository catalogRepository;
    //
    // FIXME: topics modify, method customizing
    // @KafkaListener(topics = "example-catalog-topic")
    // public void updateQty(String kafkaMessage){
    //     log.info("Kafka Message: ->" + kafkaMessage);
    //
    //     Map<String, Object> map = new HashMap<>();
    //     ObjectMapper mapper = new ObjectMapper();
    //     try{
    //         map = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {});
    //     }catch (JsonProcessingException ex){
    //         ex.printStackTrace();
    //     }
    //
    //     CatalogEntity entity = catalogRepository.findByProductId((String)map.get("productId"));
    //
    //     if(entity != null){
    //         entity.setStock(entity.getStock() - (Integer)map.get("qty"));
    //         catalogRepository.save(entity);
    //     }
    // }
}


