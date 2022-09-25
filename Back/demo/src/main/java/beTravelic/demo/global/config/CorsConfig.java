package beTravelic.demo.global.config;

import beTravelic.demo.global.util.jwt.AccessTokenInterceptor;
import beTravelic.demo.global.util.jwt.RefreshTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final AccessTokenInterceptor accessTokenInterceptor;

    private final RefreshTokenInterceptor refreshTokenInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/users/access-token");
//        registry.addInterceptor(accessTokenInterceptor).excludePathPatterns(Arrays.asList(new String[]{"/users", "/users/login", "/users/nickname/{nickname}", "/users/id", "/users/password", "/h2-console", "/swagger-ui/*", "/swagger-resources", "/swagger-resources/**", "/v3/*", "/v3", "/users/{userId}/image"}));
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("headers")
                .allowCredentials(false)
                .maxAge(3000);
    }
}
