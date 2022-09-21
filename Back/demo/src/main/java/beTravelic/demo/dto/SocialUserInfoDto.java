package beTravelic.demo.dto;

import beTravelic.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SocialUserInfoDto {
    private Long id;
    private String email;
    private String age_range;
    private String gender;
    private String access_token;

    @Builder
    public SocialUserInfoDto(Long id, String email, String gender, String age_range, String access_token){
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.age_range = age_range;
        this.access_token = access_token;
    }

}
