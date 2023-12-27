package com.teamsparta.mytodolist.domain.todo.repository

import com.teamsparta.mytodolist.domain.todo.model.TodoModel
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<TodoModel, Long> {
    
}