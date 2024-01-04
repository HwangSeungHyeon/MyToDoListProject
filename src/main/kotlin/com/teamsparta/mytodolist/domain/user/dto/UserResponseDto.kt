package com.teamsparta.mytodolist.domain.user.dto

/*
* Spring의 Web Layer의 일부
* Spring의 Service 레이어와 접근하기 위해서 사용하는 클래스
* Client의 요청(Request)을 받고, 응답(Response)을 주는 역할
*/
data class UserResponseDto(
    val id: Long, //유저 번호, 데이터베이스 상에서 붙여짐
    val name: String, //사용자 이름
    val email: String //가입할 때 사용한 이메일
)
