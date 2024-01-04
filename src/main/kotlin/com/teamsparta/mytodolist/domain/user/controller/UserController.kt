package com.teamsparta.mytodolist.domain.user.controller

import com.teamsparta.mytodolist.domain.authority.dto.TokenInfoDto
import com.teamsparta.mytodolist.domain.user.dto.LoginRequestDto
import com.teamsparta.mytodolist.domain.user.dto.SignUpRequestDto
import com.teamsparta.mytodolist.domain.user.dto.UserResponseDto
import com.teamsparta.mytodolist.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController //UserController를 Controll layer를 담당하는 Bean으로 등록
class UserController(
    private val userService: UserService
) {
    //회원가입을 담당하는 메소드
    //SignUpRequest DTO를 argument로 받아서 Service layer로부터 UserResponseDto를 받음
    //Service layer에서 전달된 DTO를 ResponseEntity로 감싸서 반환함(응답해줌)
    @PostMapping("/signup") //POST 메소드 핸들링, /signup에 접근한다
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<UserResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
//            .body(userService.signUp(signUpRequestDto, encoder))
            .body(userService.signUp(signUpRequestDto))
    }

    //로그인을 담당하는 메소드
    //LoginRequestDto를 argument로 받아서 Service Layer로부터 UserResponseDto를 받음
    //Service layer에서 전달된 DTO를 ResponseEntity로 감싸서 반환함(응답해줌)
    @PostMapping("/login") //POST 메소드 핸들링, /login에 접근한다
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<TokenInfoDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequestDto))
    }
}