package com.chiron.book.springboot.config.auth;

import com.chiron.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Scurity 설정 활성화 어노테이션
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작
                    .authorizeRequests()
                    //authorizeRequests() 선언해야 .antMatchers() 사용가능
                    //권한 관리 대상을 지정하는 옵션, permitAll() 전체 열람 권한
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    //hasRole() 해당 권한만 가능 USER권한을 갖은 사람
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //그외 권한설정 authenticated() URL들을 모두 인증된 사용자들에게 허용
                    .anyRequest().authenticated()
                .and()
                    //로그아웃 기능정의 "/"로 이동
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    //로그인 기능 설정 진입
                    .oauth2Login()
                        //로그인 성공 이후 사용자 정보를 가져올떄의 설정
                        .userInfoEndpoint()
                            //로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                            //리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                            .userService(customOAuth2UserService);
    }

}

