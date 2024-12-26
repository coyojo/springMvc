package com.example.dmaker.service;

import com.example.dmaker.entity.Developer;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  // 모든 final필드와 @NonNull 필드를 파라미터로 받는 생서자를 자동 생성
public class DMakerService {
    private final DeveloperRepository developerRepository;
    // final 키워드를 사용하면 해당 필드가 일기 전용으로 사용된다는 의도를 명확히 드러낸다.

/*   아래의 코드로 복잡하게 해야 할 것을 위의 한줄로 줄이면서 더 리팩토링이 쉬워지게된것은
  @AllArgsConstructor, @RequiredArgsConstructor 어노테이션을 붙여줌으로써 가능해짐!
    public DMakerService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

 */
    @Transactional
    public void createDeveloper(){
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("tom")
                .age(20)
                .build();

        developerRepository.save(developer);
    }



}
