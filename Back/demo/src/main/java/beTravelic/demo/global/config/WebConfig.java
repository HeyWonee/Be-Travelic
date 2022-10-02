package beTravelic.demo.global.config;

import beTravelic.demo.global.util.jwt.AccessTokenInterceptor;
import beTravelic.demo.global.util.jwt.RefreshTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
@Configuration
@RequiredArgsConstructor
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private final AccessTokenInterceptor accessTokenInterceptor;
    private final RefreshTokenInterceptor refreshTokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                );
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/users/access-token");
//        registry.addInterceptor(accessTokenInterceptor).excludePathPatterns(Arrays.asList(
//                new String[]{"/users/token", "/users/id/{id}", "/users/nickname/{nickname}", "/users/id", "/users/password", "/h2-console", "/swagger-ui.html/*", "/swagger-resources", "/swagger-resources/**", "/v3/*", "/v3", "/users/{userId}/image"}));
//    }

}
