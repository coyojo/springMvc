package com.example.dmaker.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Slf4j
//@RequiredArgsConstructor   필수적인 값을 담은 생성자를 생성 (final 필드나 @NonNull 붙은 필드가 필수)
public class DevDto {
     String name;
     Integer age;
     Integer experienceYears;
     LocalDateTime startAt;

     public void printLog(){
          log.info(getName());
     }

}
