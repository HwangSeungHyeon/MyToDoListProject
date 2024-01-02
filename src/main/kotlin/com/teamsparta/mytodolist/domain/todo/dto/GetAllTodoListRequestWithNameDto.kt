package com.teamsparta.mytodolist.domain.todo.dto

/*
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 목록 필터링 및 정렬에 대한 요청을 전달하는 DTO
*/
data class GetAllTodoListRequestWithNameDto(
    val name: String, //작성자를 기준으로 필터하기 위해서 사용하는 변수
    val sortByDescend: Boolean //내림차순으로 정렬이면 true, 오름차순으로 정렬이면 false
)
