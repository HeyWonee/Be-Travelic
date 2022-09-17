package beTravelic.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SocialUserInfoDto {
    private Long id;
    private String email;
    private String age_range;
    private String gender;

    public SocialUserInfoDto(Long id, String email, String gender, String age_range){
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.age_range = age_range;
    }
}
