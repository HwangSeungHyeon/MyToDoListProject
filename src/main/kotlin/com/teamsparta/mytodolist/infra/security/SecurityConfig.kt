package com.teamsparta.mytodolist.infra.security

import com.teamsparta.mytodolist.domain.authority.common.JwtAuthenticationFilter
import com.teamsparta.mytodolist.domain.authority.common.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

//https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html 참조
//https://m.blog.naver.com/kimnx9006/220638156019 얘도 참조
//https://www.youtube.com/watch?v=DA2yR7zORkQ 얘도 참조
//https://velog.io/@dhk22/TIL-Day-71-Kotlin-03-Spring-Security-적용하기-Jwt방식-인증 참조

@Configuration
@EnableWebSecurity //기본적인 Web 보안을 활성화하겠다는 어노테이션
class SecurityConfig(
    private val tokenProvider: TokenProvider
){
    private val allowedUrls = arrayOf("/", "/swagger-ui/**", "/v3/**", "/sign-up", "/login") // sign-up, sign-in 추가
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin{ it.disable() }
            .httpBasic{ it.disable() }

            //https://developer-youn.tistory.com/156 참조
            //https://kchanguk.tistory.com/197 참조
            //사이트간 위조 요청에 대한 예방 기능이지만, 토큰 인증 방식은 서버에 인증 정보를 보관하지 않기 때문에, CSRF 공격에 대해 어느정도 안전하다.
            .csrf{ it.disable() }

            .cors { it.disable() }

            .headers { it.frameOptions { options -> options.sameOrigin() } }

            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            .authorizeHttpRequests{
                it.requestMatchers(*allowedUrls).permitAll()
                    .anyRequest().authenticated()
            }

            //addFilterBefore는 뒤에 있는 필터가 실행되기 전에 앞에 있는 필터를 실행
            .addFilterBefore(
                JwtAuthenticationFilter(tokenProvider),
//                UsernamePasswordAuthenticationFilter::class.java,
                BasicAuthenticationFilter::class.java
            )
//        http {
//            authorizeRequests {
//                authorize("/swagger-ui/**", permitAll) //swagger 페이지는 테스트를 위해서 무조건 접근 허용
//                authorize("/v3/**", permitAll) // /v3/api-docs는 테스트를 위해서 무조건 접근 허용
//                authorize("/todos", permitAll) //할 일 목록을 보는 건 누구나 가능하니 무조건 접근을 허용
//                authorize("/todos/{todoId}/comments", permitAll) //댓글 보는 건 누구나 가능하니 무조건 접근을 허용
//
//                authorize("/signup", anonymous) //회원가입은 인증되지 않은 사용자만 접근 가능
//                authorize("/login", anonymous) //로그인은 인증되지 않은 사용자만 접근 가능
//
//                authorize(anyRequest, authenticated) //그 외에는 인증된 사용자만 접근을 허용
//            }
//
//            //https://developer-youn.tistory.com/156 참조
//            //https://kchanguk.tistory.com/197 참조
//            //사이트간 위조 요청에 대한 예방 기능이지만, 토큰 인증 방식은 서버에 인증 정보를 보관하지 않기 때문에, CSRF 공격에 대해 어느정도 안전하다.
//            csrf { disable() }
//            sessionManagement { SessionCreationPolicy.STATELESS } //쿠키나 세션을 사용하지 않으므로 STATELESS 설정
//            httpBasic { disable() }
//
//            //addFilterBefore는 뒤에 있는 필터가 실행되기 전에 앞에 있는 필터를 실행
//            addFilterBefore(JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
//        }
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder() //비밀번호를 암호화하기 위한 인터페이스인 PasswordEncorder의 구현체
}