package com.teamsparta.mytodolist.domain.todo.controller

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoResponseDto
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos") // base URI 경로 하위에 해당하는 건 전부 TodoController가 담당하게 됨
@RestController //TodoController를 Bean으로 등록
class TodoController {
    @GetMapping() //GET 메소드 핸들링, /todos에 접근한다
    //할 일 목록 리스트를 가져오는 메소드
    //TodoResponseDto 리스트를 반환
    fun getTodoList(): List<TodoResponseDto> {
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @GetMapping("/{todoId}") //GET 메소드 핸들링, /todos/{todoId}에 접근한다
    //선택한 할 일 카드를 가져오는 메소드
    //todoId를 argument로 받아서 TodoResponseDto를 반환
    fun getTodo(@PathVariable todoId: Long): TodoResponseDto{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PostMapping() //POST 메소드 핸들링, /todos에 접근한다
    //할 일 카드를 만드는 메소드
    //CreateTodoResponse DTO를 argument로 받아서 TodoResponseDto를 반환
    fun createTodo(@RequestBody createTodoResponse: CreateTodoResponseDto): TodoResponseDto{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PutMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할 일 카드를 수정하는 메소드
    //todoId와 UpdateTodoResponsef DTO를 argument로 받아서 TodoResponseDto를 반환
    fun updateTodo(@PathVariable todoId: Long, @RequestBody updateTodoResponse: UpdateTodoResponseDto): TodoResponseDto{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @DeleteMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할 일을 삭제하는 메소드
    fun deleteTodo(@PathVariable todoId: Long){
        TODO("not implemented") //아직 기능 구현 안 함
    }
}