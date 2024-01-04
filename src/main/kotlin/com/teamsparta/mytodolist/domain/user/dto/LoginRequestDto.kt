package com.teamsparta.mytodolist.domain.user.dto

/*
* Spring의 Web Layer의 일부
* Spring의 Service 레이어와 접근하기 위해서 사용하는 클래스
* Client의 요청(Request)을 받고, 응답(Response)을 주는 역할
*/
data class LoginRequestDto(
    val email: String, //로그인할 때는 이메일과 비밀번호만 있으면 됨
    val password: String //로그인할 때는 이메일과 비밀번호만 있으면 됨
)
