package com.example.dmaker.controller;

//Controller = 사용자의 입력을 최초로 받는 곳

import com.example.dmaker.entity.Developer;
import com.example.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// RestController는 @Controller + @ResponseBody 가 합쳐진 것으로 @ResponseBody가 붙으면
 // 주로 json으로 응답을 받는다는것을 상정한다는 것
@Slf4j
@RestController
@RequiredArgsConstructor
 public class DMakerController {
    private final DMakerService dMakerService;
    @GetMapping("/developers")
     public List<String> getAllDevelopers(){
        log.info("GET/ developers HTTP/1.1");
        return Arrays.asList("snow", "Elsa", "Olaf");
    }

    @GetMapping("/create-developers")
    public List<String> createDevelopers(){
        log.info("GET/ create-developers HTTP/1.1");
        dMakerService.createDeveloper();
        return Collections.singletonList("tom");
    }
 }

