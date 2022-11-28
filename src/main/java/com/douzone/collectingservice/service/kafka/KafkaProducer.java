package com.douzone.collectingservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String send(String topic, String status, List<String> prescribeCode){

        Map<String, Object> data = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        data.put("\"prescribeCodeList\"", prescribeCode);
        data.put("\"status\"", status);

        try{
            json = mapper.writeValueAsString(data);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, json);

        return status;
    }


    // public UserDto send(String topic, UserDto userDto){
    //     ObjectMapper mapper = new ObjectMapper();
    //     String jsonInString = "";
    //
    //     try{
    //         jsonInString = mapper.writeValueAsString(userDto);
    //     }catch (JsonProcessingException e){
    //         e.printStackTrace();
    //     }
    //
    //     kafkaTemplate.send(topic, jsonInString);
    //
    //     return userDto;
    // }


}
