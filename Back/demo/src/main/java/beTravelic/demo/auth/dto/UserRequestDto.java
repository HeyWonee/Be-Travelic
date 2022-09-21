package beTravelic.demo.auth.dto;

import beTravelic.demo.domain.User;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @ApiParam(value = "사용자 email", required = true)
    private String email;
    @ApiParam(value = "사용자 pw", required = true)
    private String pw;

    @ApiParam(value = "사용자 연령대")
    private String age_range;

    @ApiParam(value = "사용자 성별")
    private String gender;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .pw(passwordEncoder.encode(pw))
                .age_range((age_range))
                .gender(gender)
                .build();
    }

    public User toOauthUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .pw(passwordEncoder.encode(pw))
                .age_range((age_range))
                .gender(gender)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pw);
    }
}
