package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoRequestDto
import org.springframework.stereotype.Service

@Service //todoService를 bean으로 설정
interface TodoService {
    fun getAllTodoList(): List<TodoResponseDto>

    fun getTodoById(id: Long): TodoResponseDto

    fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto

    fun updateTodo(id: Long, requestDto: UpdateTodoRequestDto): TodoResponseDto

    fun deleteTodo(id: Long)
}