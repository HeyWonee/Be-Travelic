package beTravelic.demo.domain.service;

import beTravelic.demo.domain.dto.SignUpRequestDto;
import beTravelic.demo.domain.dto.SignupResponseDto;
import beTravelic.demo.domain.dto.SurveySaveRequestDto;
import beTravelic.demo.domain.dto.SurveySaveResponseDto;
import beTravelic.demo.domain.entity.*;
import beTravelic.demo.domain.repository.UserCategoryRepository;
import beTravelic.demo.domain.repository.UserKeywordRepository;
import beTravelic.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final UserRepository userRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final UserCategoryRepository userCategoryRepository;

    public SurveySaveResponseDto surveySave(String id, SurveySaveRequestDto dto){
        UserCategories userCategories = dto.toUserCategoriesEntity();
        UserKeywords userKeywords = dto.toUserKeywordEntity();

        User user = userRepository.findUserById(id).orElseThrow(() ->
                new RuntimeException("일치하는 사용자가 없음"));

        userCategories.setUser(user);
        userKeywords.setUser(user);
        userCategoryRepository.save(userCategories);
        userKeywordRepository.save(userKeywords);

        return new SurveySaveResponseDto(id);
    }

}