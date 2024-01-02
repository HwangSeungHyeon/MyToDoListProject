package com.teamsparta.mytodolist.domain.todo.dto

/*
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 목록을 정렬할 때에 대한 요청을 전달하는 DTO
*/
data class ListSortRequestDto(
    var sortByDescend: Boolean //내림차순으로 정렬이면 true, 오름차순으로 정렬이면 false
)
