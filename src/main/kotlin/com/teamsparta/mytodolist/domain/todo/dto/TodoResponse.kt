package com.teamsparta.mytodolist.domain.todo.dto

import java.util.Date

//할 일 카드를 가져올 때 사용하는 DTO
data class TodoResponse(
    val id: Long, //할 일 목록 번호
    val title: String, //할 일 제목을 나타내는 변수
    val description: String, //할 일 내용을 나타내는 변수
    val date: Date, //작성일을 나타내는 변수
    val name: String //작성자 이름을 저장할 변수
)
