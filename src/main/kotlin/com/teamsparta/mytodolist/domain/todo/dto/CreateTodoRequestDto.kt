package com.teamsparta.mytodolist.domain.todo.dto

//DTO: 각 Layer 사이의 데이터를 전달하는데 사용
//요청(Request)과 응답(Response) 또한 DTO로 표현 가능
//할 일 카드 생성 요청(request)을 전달하는 클래스
data class CreateTodoRequestDto(
    val title: String, //할 일 제목을 나타내는 변수
    val description: String, //할 일 내용을 나타내는 변수
    val name: String //작성자 이름을 저장할 변수
)
