package com.example.dmaker.service;

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.entity.Developer;
import com.example.dmaker.exception.DMakerErrorCode;
import com.example.dmaker.exception.DMakerException;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final EntityManager em;
    /*
    Transactional의 개념
    ACID
    A - atomic 원자성
    C - constistency 일관성
    I- Isolation 고립성
    D - durability 지속성
     */

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request){
                validateCreateDeveloperRequest(request);
             //business logic start
             Developer developer = Developer.builder()
                     .developerLevel(request.getDeveloperLevel())
                     .developerSkillType(request.getDeveloperSkillType())
                     .experienceYears(request.getExperienceYears())
                     .memberId(request.getMemberId())
                     .name(request.getName())
                     .age(request.getAge())
                     .build();

             developerRepository.save(developer);
             return CreateDeveloper.Response.fromEntity(developer);
             //business logic end



    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {//business validation을 수행


        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();

        if (developerLevel ==DeveloperLevel.SENIOR
        && experienceYears < 10 ){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGNIOR
            && (experienceYears < 4 || experienceYears >10)){
        throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

       /*
        Optional<Developer> developer = developerRepository.findByMemberId(request.getMemberId());
        if(developer.isPresent()) throw new DMakerException(DMakerErrorCode.DUPLICATED_MEMBER_ID);
            아래의 코드와 같은 걸 의미하는데 아래처럼도 쓸 수 있다는 뜻
        */
        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DMakerErrorCode.DUPLICATED_MEMBER_ID);
                }));




    }


    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()//List<Developer> 반환
                .stream()//Stream<Developer>로 변환
                .map(DeveloperDto::fromEntity)//DeveloperDto::fromEntity는 DeveloperDto클래스의 fromEntitiy라는 정적 메서드를 호출하라는 의미
                // 각 Developer를 DeveloperDto로 매핑
                .collect(Collectors.toList()); // Stream<DeveloperDto>를 List<DeveloperDto>로 변환
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
    }
}
