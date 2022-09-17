package beTravelic.demo.controller;

import beTravelic.demo.service.KakaoUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthContoller {
    private final KakaoUserService kakaoUserService;

    /** 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code){
        System.out.println(code);
        return KakaoUserService.kakaoCallback(code);
    }
}
