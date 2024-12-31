package com.example.dmaker.controller;

//Controller = 사용자의 입력을 최초로 받는 곳

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.service.DMakerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController는 @Controller + @ResponseBody 가 합쳐진 것으로 @ResponseBody가 붙으면
 // 주로 json으로 응답을 받는다는것을 상정한다는 것
@Slf4j
@RestController
@RequiredArgsConstructor
 public class DMakerController {
    private final DMakerService dMakerService;
    @GetMapping("/developers")
     public List<DeveloperDto> getAllDevelopers(){
        log.info("GET/ developers HTTP/1.1");
        return dMakerService.getAllDevelopers();
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(@Valid @RequestBody CreateDeveloper.Request request){
        log.info("request : {}", request);
        return dMakerService.createDeveloper(request);
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId){
        log.info("GET/ developers/memberId HTTP/1.1");
    return dMakerService.getDeveloperDetail(memberId);
    }

 }

