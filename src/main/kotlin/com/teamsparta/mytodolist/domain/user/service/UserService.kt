package com.teamsparta.mytodolist.domain.user.service

import com.teamsparta.mytodolist.domain.authority.dto.TokenInfoDto
import com.teamsparta.mytodolist.domain.user.dto.LoginRequestDto
import com.teamsparta.mytodolist.domain.user.dto.SignUpRequestDto
import com.teamsparta.mytodolist.domain.user.dto.UserResponseDto

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
*/
interface UserService {

    //회원가입을 담당하는 메소드
    //Controller로부터 회원가입 정보가 담긴 요청(Request) DTO를 받는다.
    //회원가입 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    //그 후 응답(Response) DTO를 Controller에 전달
    fun signUp(signUpRequestDto: SignUpRequestDto): UserResponseDto

    //로그인을 담당하는 메소드
    //controller로부터 로그인 정보가 담긴 요청(Request) DTO를 받는다.
    //로그인 요청(Request) DTO에 있는 값으로 토큰이 들어있는 TokenInfoDto 생성
    //그 후 TokenInfoDto를 Controller에 전달
    fun login(loginRequestDto: LoginRequestDto): TokenInfoDto
}