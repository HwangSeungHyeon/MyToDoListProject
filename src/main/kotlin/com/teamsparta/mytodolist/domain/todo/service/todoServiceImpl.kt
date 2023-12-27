package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto


//todoService 인터페이스 파일을 오버라이딩해서 구현
class todoServiceImpl : todoService{
    
    override fun getAllTodoList(): List<TodoResponseDto> {
        // TODO: DB에서 모든 할 일 목록을 조회하여 TodoResponseDto로 변환 후 리스트 형태로 반환
        TODO("Not yet implemented")
    }
    
    override fun getTodoById(id: Long): TodoResponseDto {
        // TODO: 만약 id에 해당하는 할 일이 없다면 throw ModelNotFoundException
        // TODO: DB에서 ID 기반으로 할 일을 조회하여 TodoResponseDto로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto {
        // TODO: CreateTodoRequestDto를 TodoResponseDto로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun updateTodo(id: Long): TodoResponseDto {
        // TODO: 만약 id에 해당하는 할 일이 없다면 throw ModelNotFoundException
        // TODO: DB에서 ID 기반으로 할 일을 조회하여 TodoResponseDto로 변환 후 반환     
        TODO("Not yet implemented")
    }

    override fun deleteTodo(id: Long): TodoResponseDto {
        // TODO: 만약 id에 해당하는 할 일이 없다면 throw ModelNotFoundException
        // TODO: DB에서 ID에 해당하는 할 일을 삭제
        TODO("Not yet implemented")
    }
}