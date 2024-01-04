package com.teamsparta.mytodolist.domain.authority.common

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter

@Order(0)
@Component
class JwtAuthenticationFilter(
    private val tokenProvider: TokenProvider
): OncePerRequestFilter() { //GenericFilterBean을 상속받음

    //실제 필터링 로직은 doFilter에서 수행
    //jwt의 인증 정보를 현재 실행중인 스레드(Security Context)에 저장
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        //Request Header 에서 토큰을 꺼냄
        val token = resolveToken(request)

        //validateToken 으로 토큰 유효성 검사
        if (token != null && tokenProvider.validateToken(token)) { //null이 없다면 정상 토큰으로 본다
            val authentication = tokenProvider.getAuthentication(token) //정상 토큰이면 해당 토큰으로 Authentication을 가져와서
            SecurityContextHolder.getContext().authentication = authentication //SecurityContextHolder에 저장
        }
        chain.doFilter(request, response)
    }


    // HttpServletRequest 객체의 Header에서 token을 꺼내는 역할을 수행하는 메소드
    private fun resolveToken(request: HttpServletRequest): String?{
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)

        return if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer")){
            bearerToken.substring(7) //뒤에 있는 키 값만 가져옴
        } else null
    }

}