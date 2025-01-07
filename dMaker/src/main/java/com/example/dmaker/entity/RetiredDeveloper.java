package com.example.dmaker.entity;

import com.example.dmaker.code.StatusCode;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//퇴직한 개발자들 모아놓는 테이블
@Getter
@Setter
@Builder  // builder를 쓰려면 @NoArgsConstructor, @AllArgsConstructor 두개를 꼭 써줘야 한다
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RetiredDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    private String memberId;
    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    @CreatedDate
    private LocalDateTime cratedAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;



}
