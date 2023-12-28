package com.teamsparta.mytodolist.domain.comment.dto

/*
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
* 다른 layer로 데이터를 전달할 때(응답할 때) 사용하는 클래스
*/
data class CommentResponseDto(
    val id: Long, //댓글 번호
    val content: String, //댓글 내용을 나타내는 변수
    val name: String, //작성자 이름을 나타내는 변수
    val password: String //작성자의 비밀번호를 나타내는 변수
)
