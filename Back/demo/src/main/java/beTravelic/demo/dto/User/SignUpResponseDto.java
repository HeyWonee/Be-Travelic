package beTravelic.demo.dto.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDto {
    private String refreshToken;
    private String accessToken;

    public static SignUpResponseDto of(String accessToken, String refreshToken){
        return SignUpResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
