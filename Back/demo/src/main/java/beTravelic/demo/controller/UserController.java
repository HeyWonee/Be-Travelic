package beTravelic.demo.controller;

import beTravelic.demo.dto.User.SignUpRequestDto;
import beTravelic.demo.global.common.CommonResponse;
import beTravelic.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<CommonResponse> signup(@ModelAttribute @Validated SignUpRequestDto dto) throws IOException {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(UserService.g)
    }
}
