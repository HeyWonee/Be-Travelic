package beTravelic.demo.service;

import beTravelic.demo.dto.SocialUserInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialService {
    private final KakaoService kakaoService;
    private final ObjectMapper objectMapper;

    public SocialUserInfoDto verificationKakao(String code) {
        SocialUserInfoDto socialUserInfoDto = new SocialUserInfoDto();

        String accessToken = kakaoService.getAccessTokenByCode(code);
        String userInfo = kakaoService.getUserInfoByAccessToken(accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(userInfo);
            String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
            socialUserInfoDto.setEmail(email);
            String age_range = String.valueOf(jsonNode.get("kakao_account").get("age_range"));
            socialUserInfoDto.setAge_range(age_range);
            String gender = String.valueOf(jsonNode.get("kakao_account").get("gender"));
            socialUserInfoDto.setGender(gender);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return socialUserInfoDto;
    }
}
