package com.teamsparta.mytodolist.domain.comment.dto

data class DeleteCommentRequestDto(
    val name: String, //작성자 이름을 나타내는 변수
    val password: String //작성자의 비밀번호를 나타내는 변수
)
