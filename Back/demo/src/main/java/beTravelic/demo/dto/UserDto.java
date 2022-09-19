package beTravelic.demo.dto;

import beTravelic.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String age_range;
    private String gender;

    private String pw;


    @Builder
    public UserDto(Long id, String email, String gender, String age_range, String pw){
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.gender = gender;
        this.age_range = age_range;
    }
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
