package example.ganada.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/*
 Authorization
 웹요청에 대한 보안 구성
* */
@Configuration
@RequiredArgsConstructor
public class HttpSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (webSecurity) -> webSecurity.ignoring()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/configuration/ui")
//                .antMatchers("/v3/api-docs", "/configuration/ui", "/swagger-resources/**", "/swagger-ui/**",
//                        "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger/**")
                .antMatchers("/static/css/**, /static/js/**, *.ico")
                .antMatchers("/api/v1/auth/login", "/api/v1/auth/signup")
                ;
    }

}