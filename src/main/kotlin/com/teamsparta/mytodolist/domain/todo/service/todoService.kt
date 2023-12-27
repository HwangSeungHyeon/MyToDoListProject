package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto

interface todoService {
    fun getAllTodoList(): List<TodoResponseDto>

    fun getTodoById(id: Long): TodoResponseDto

    fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto

    fun updateTodo(id: Long,): TodoResponseDto

    fun deleteTodo(id: Long): TodoResponseDto
}