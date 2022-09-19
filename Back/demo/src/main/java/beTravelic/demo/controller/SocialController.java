package beTravelic.demo.controller;

import beTravelic.demo.domain.User;
import beTravelic.demo.dto.SocialUserInfoDto;
import beTravelic.demo.dto.UserDto;
import beTravelic.demo.repository.UserRepository;
import beTravelic.demo.service.SocialService;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SocialController {
    private final SocialService socialService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @PostMapping("/oauth/kakao/callback")
    public ResponseEntity socialLogin(@PathVariable String provider,
                                      @RequestBody(required = false) UserDto userDto,
                                      @RequestParam String code,
                                      HttpServletResponse response){
        SocialUserInfoDto socialUserInfoDto = null;

        if (provider.equals("kakao")) {
            socialUserInfoDto = socialService.verificationKakao(code);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<User> users = userRepository.findByUserEmail(socialUserInfoDto.getEmail());

        // 회원가입 여부 확인
        if (users.isEmpty()) {
            userDto.setEmail(socialUserInfoDto.getEmail());
            userDto.setAge_range(socialUserInfoDto.getAge_range());
            userDto.setGender(socialUserInfoDto.getGender());
            userDto.setPw(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()));
            User user = userDto.toEntity();
            userRepository.save(user);
        }

        // jwt 토큰 생성
        String token = Jwts.builder()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .withClaim("email", socialUserInfoDto.getEmail())
                .sign(JCEMapper.Algorithm.HMAC512(env.getProperty("token.secret")));

        return new ResponseEntity(HttpStatus.OK);
    }
}
