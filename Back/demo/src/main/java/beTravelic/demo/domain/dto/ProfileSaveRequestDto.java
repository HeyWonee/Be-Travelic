package beTravelic.demo.domain.dto;

import beTravelic.demo.domain.entity.Picture;
import beTravelic.demo.domain.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileSaveRequestDto {

    private MultipartFile picture;

    @Builder
    public User toUserPicture(){
        return User.ProfileSave().build();
    }

}
