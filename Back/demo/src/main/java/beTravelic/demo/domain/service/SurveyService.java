package beTravelic.demo.domain.service;

import beTravelic.demo.domain.dto.SurveySaveRequestDto;
import beTravelic.demo.domain.dto.SurveySaveResponseDto;
import beTravelic.demo.domain.entity.*;
import beTravelic.demo.domain.repository.SurveyRepository;
import beTravelic.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    public SurveySaveResponseDto surveySave(String id, SurveySaveRequestDto dto){
        Survey survey = dto.toSurveyEntity();

        User user = userRepository.findUserById(id).orElseThrow(() ->
                new RuntimeException("일치하는 사용자가 없음"));
        survey.setUser(user);
        survey.setSurvey_category(dto.getCategory());
        survey.setSurvey_keyword(dto.getKeyword());
        surveyRepository.save(survey);

        return new SurveySaveResponseDto(survey.getId());
    }

}
