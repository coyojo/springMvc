package com.example.dmaker.controller;

//Controller = 사용자의 입력을 최초로 받는 곳

import com.example.dmaker.dto.*;
import com.example.dmaker.exception.DMakerException;
import com.example.dmaker.service.DMakerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        return dMakerService.getAllEmployedDevelopers();
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


    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request){
        log.info("PUT/developers HTTP/1.1");
        return dMakerService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper( @PathVariable String memberId){
        log.info("delete/developers HTTP/1.1");
        return dMakerService.deleteDeveloper(memberId);
    }




 }

