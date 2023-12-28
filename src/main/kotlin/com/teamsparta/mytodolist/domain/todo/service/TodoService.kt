package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoStatusRequestDto

/*
* Spring의 Web Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
*/
interface TodoService {
    //모든 할 일 목록을 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO 리스트로 바꾸고, Controller로 전달
    fun getAllTodoList(): List<TodoResponseDto>
    
    //id에 해당하는 할 일 카드를 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO로 바꾸고, Controller로 전달
    fun getTodoById(id: Long): TodoResponseDto

    /*
    * 할 일 카드를 생성하는 메소드
    * Controller로부터 생성 요청(Request) DTO를 받는다.
    * 생성 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto

    /* 
    * id에 해당하는 할 일 카드를 수정하는 메소드
    * Controller로부터 수정 요청(Request) DTO를 받는다.
    * 수정 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    fun updateTodo(id: Long, requestDto: UpdateTodoRequestDto): TodoResponseDto

    fun updateTodoStatus(id: Long, requestDto: UpdateTodoStatusRequestDto): TodoResponseDto

    //id에 해당하는 할 일 카드를 삭제하는 메소드
    //Entity로 DB에서 값을 가져와서 삭제한다.
    fun deleteTodo(id: Long)
}