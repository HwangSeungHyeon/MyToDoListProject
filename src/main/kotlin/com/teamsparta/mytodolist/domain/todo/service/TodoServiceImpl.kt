package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.exception.ModelNotFoundException
import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.model.TodoModel
import com.teamsparta.mytodolist.domain.todo.model.toResponse
import com.teamsparta.mytodolist.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


//todoService 인터페이스 파일을 오버라이딩해서 구현
@Service //TodoServiceImpl를 bean으로 설정
class TodoServiceImpl(
    private val todoRepository: TodoRepository //상속성 주입
) : TodoService{

    override fun getAllTodoList(): List<TodoResponseDto> {
        return todoRepository.findAll().map { it.toResponse() }
    }

    override fun getTodoById(id: Long): TodoResponseDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto {
        return todoRepository.save(
            TodoModel(
                title = requestDto.title,
                description = requestDto.description,
                date = requestDto.date,
                name = requestDto.name
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(id: Long, requestDto: UpdateTodoRequestDto): TodoResponseDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)

        todo.name = requestDto.name
        todo.title = requestDto.title
        todo.description = requestDto.description

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(id: Long){
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)
        todoRepository.delete(todo)
    }
}