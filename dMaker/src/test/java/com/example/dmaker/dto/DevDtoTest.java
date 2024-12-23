package com.example.dmaker.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DevDtoTest {
    @Test
    void test(){
      /*
        DevDto devDto = new DevDto();

        devDto.setName("snow");
        devDto.setAge(28);
        devDto.setStartAt(LocalDateTime.now());
    */
        //builder와 build를 사용하면 다음과 같이 생성 가능
       final DevDto devDto = DevDto.builder()
                        .name("snow")
                        .age(21)
                        .experienceYears(2)
                        .startAt(LocalDateTime.now())
                        .build();

        System.out.println(devDto);
        devDto.printLog();
    }
}