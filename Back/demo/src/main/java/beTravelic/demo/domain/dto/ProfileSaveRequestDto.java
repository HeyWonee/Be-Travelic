package beTravelic.demo.domain.dto;

import beTravelic.demo.domain.entity.Picture;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileSaveRequestDto {

    private MultipartFile picture;




}
