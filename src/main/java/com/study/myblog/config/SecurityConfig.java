package com.study.myblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //loC
public class SecurityConfig {

    // 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean   // loC가 됨.
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // 1. csrf 비활성화
        http.csrf().disable();

        // 2. 인증 주소 설정
        http.authorizeRequests(
                authorize -> authorize.antMatchers("/", "/auth/**", "/js/**", "/css/**, ", "/static/image/**", "/dummy/**").permitAll()
                        .anyRequest().authenticated()
        );

        // 3. 로그인 처리 프로세스 설정
        http.formLogin(f -> f.loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/")

        );

        return http.build();
    }
}

/*
http
        .csrf().disable()   // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/js/**", "/css/**, ", "/image/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해줌.
                    .defaultSuccessUrl("/");
        return http.build();*/
