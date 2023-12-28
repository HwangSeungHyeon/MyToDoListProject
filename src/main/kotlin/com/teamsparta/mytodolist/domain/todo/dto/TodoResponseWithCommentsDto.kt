package com.teamsparta.mytodolist.domain.todo.dto

import com.teamsparta.mytodolist.domain.comment.model.CommentModel
import java.time.LocalDateTime

data class TodoResponseWithCommentsDto (
    val id: Long, //할 일 목록 번호
    val title: String, //할 일 제목을 나타내는 변수
    val description: String, //할 일 내용을 나타내는 변수
    val date: LocalDateTime, //작성일을 나타내는 변수
    val name: String, //작성자 이름을 나타내는 변수
    val status: Boolean,
    val comments: MutableList<CommentModel>
)