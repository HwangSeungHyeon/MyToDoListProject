package com.teamsparta.mytodolist.domain.comment.dto

/*
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
* 댓글 작성 요청(request)을 전달하는 클래스
*/
data class AddCommentRequestDto(
    val content: String, //댓글 내용을 나타내는 변수
    val name: String, //작성자 이름을 나타내는 변수
    val password: String //작성자의 비밀번호를 나타내는 변수
)
