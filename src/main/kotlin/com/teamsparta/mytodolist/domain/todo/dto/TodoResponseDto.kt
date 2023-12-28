package com.teamsparta.mytodolist.domain.todo.dto

import com.teamsparta.mytodolist.domain.comment.model.CommentModel
import java.time.LocalDateTime

/*
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
* 다른 layer로 데이터를 전달할 때(응답할 때) 사용하는 클래스
*/
data class TodoResponseDto(
    val id: Long, //할 일 목록 번호
    val title: String, //할 일 제목을 나타내는 변수
    val description: String, //할 일 내용을 나타내는 변수
    val date: LocalDateTime, //작성일을 나타내는 변수
    val name: String, //작성자 이름을 나타내는 변수
    val status: Boolean,
    val comments: MutableList<CommentModel>
)
