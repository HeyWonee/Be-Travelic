//package beTravelic.demo.service;
//
//import beTravelic.demo.domain.User;
//import beTravelic.demo.dto.SocialUserInfoDto;
//import beTravelic.demo.repository.UserRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.UUID;
//
//public class KakaoUserService {
//    private final UserRepository userRepository;
//
//    public KakaoUserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public SocialUserInfoDto kakaoCallback(String code) throws JsonProcessingException {
//        // 1. 인가코드로 엑세스 토큰 요청
//        String accessToken = getAccessToken(code);
//
//        // 2. 토큰으로 카카오 api 호출
//        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
//
//        // 3. 강제 로그인 처리
//
//        return kakaoUserInfo;
//    }
//
//    private String getAccessToken(String code) throws JsonProcessingException {
//        // HTTP Header 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HTTP Body 생성
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", "D205");
//        body.add("redirect_uri", "http://localhost:8080/oauth/kakao/callback");
//        body.add("code", code);
//
//        // HTTP 요청 보내기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        // HTTP 응답 (JSON) -> 엑세스 토큰 파싱
//        String responseBody = response.getBody();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(responseBody);
//        return jsonNode.get("access_token").asText();
//    }
//
//    // 2. 토큰으로 카카오 api 호출
//    private SocialUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
//        // HTTP Header 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HTTP 요청 보내기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(headers);
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        // responseBody에 있는 정보 꺼내기
//        String responseBody = response.getBody();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(responseBody);
//
//        Long id = jsonNode.get("id").asLong();
//        String email = jsonNode.get("kakao_account").get("email").asText();
//        String age_range = jsonNode.get("kakao_account").get("age_range").asText();
//        String gender = jsonNode.get("kakao_account").get("gender").asText();
//
//        return new SocialUserInfoDto(id, email, age_range, gender);
//    }
//
//    // 3. 카카오id로 회원가입 처리
//    private User registerKakaoUserIfNeed (SocialUserInfoDto kakaoUserInfo){
//        // 중복된 email 있는지 확인
//        String kakaoEmail = kakaoUserInfo.getEmail();
//        String age_range = kakaoUserInfo.getAge_range();
//        String gender = kakaoUserInfo.getGender();
//        User kakaoUser = (User) userRepository.findByUserEmail(kakaoEmail)
//                .orElse(null);
//
//        if (kakaoUser == null) {
//            // 회원가입
//            String password = UUID.randomUUID().toString();
//            String encodedPassword = passwordEncoder.encode(password);
//            kakaoUser = new User(kakaoEmail, age_range, gender, encodedPassword);
//            userRepository.save(kakaoUser);
//        }
//        return kakaoUser;
//    }
//    // 강제 로그인 처리
//    private Authentication forceLogin(User kakaoUser) {
//        UserDetails userDetails = new UserServiceImpl(kakaoUser);
//                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return authentication;
//    }
//    // response header에 jwt 토큰 추가
//    private void kakaoUserAuthorizationInput(Authentication authentication, HttpServletResponse response) {
//
//    }
//
//
//}
