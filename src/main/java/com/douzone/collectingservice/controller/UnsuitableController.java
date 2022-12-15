package com.douzone.collectingservice.controller;

import com.douzone.collectingservice.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/collecting-service/")
@RequiredArgsConstructor
public class UnsuitableController {

    private final SampleService service;

    @PostMapping("/unsuitable-reason-management/")
    public void insertUnsuitableSample(@RequestBody List<Map<String, String>> unsuitInfo) {
        System.out.println(unsuitInfo);
        service.insertUnsuitableSample(unsuitInfo);
    }



}
