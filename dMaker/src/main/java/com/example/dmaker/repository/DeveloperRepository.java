package com.example.dmaker.repository;

import com.example.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    //Developer 엔티티와 관련된 작업을 처리하기 위한 인터페이스이므로 타입에 Developer를 적어준다

}
