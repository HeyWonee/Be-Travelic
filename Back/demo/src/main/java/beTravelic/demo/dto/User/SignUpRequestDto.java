package beTravelic.demo.dto.User;

import beTravelic.demo.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class SignUpRequestDto {
    private Long id;
    private String email;
    private String age_range;
    private String gender;

    private String pw;


    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .gender(gender)
                .email(email)
                .age_range(age_range)
                .build();
    }
}
