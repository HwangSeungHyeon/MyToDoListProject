package com.teamsparta.mytodolist.domain.todo.dto

// 할일 카드를 수정했을 때 사용하는 DTO
data class UpdateTodoResponse(
    val title: String, //할일 제목을 나타내는 변수
    val description: String, //할일 내용을 나타내는 변수
    val name: String //작성자 이름을 저장할 변수
)
