package com.teamsparta.mytodolist.domain.todo.controller

import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoResponse
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponse
import com.teamsparta.mytodolist.domain.todo.dto.UpdateTodoResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos") // base URI 경로 하위에 해당하는 건 전부 TodoController가 담당하게 됨
@RestController //TodoController를 Bean으로 등록
class TodoController {
    @GetMapping() //GET 메소드 핸들링, /todos에 접근한다
    //할일 목록 리스트를 가져오는 메소드
    //Entity를 반환(Repository에 접근해야 하니까)
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @GetMapping("/{todoId}") //GET 메소드 핸들링, /todos/{todoId}에 접근한다
    //선택한 할일 카드를 가져오는 메소드
    //todoId를 argument로 받아서 Entity를 반환(Repository에 접근해야 하니까)
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse>{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PostMapping() //POST 메소드 핸들링, /todos에 접근한다
    //할일 카드를 만드는 메소드
    //CreateTodoResponse DTO를 argument로 받아서 Entity를 반환(Repository에 접근해야 하니까)
    fun createTodo(@RequestBody createTodoResponse: CreateTodoResponse): ResponseEntity<CreateTodoResponse>{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PutMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할일 카드를 수정하는 메소드
    //todoId와 UpdateTodoResponsef DTO를 argument로 받아서 Entity를 반환(Repository에 접근해야 하니까)
    fun updateTodo(@PathVariable todoId: Long, @RequestBody updateTodoResponse: UpdateTodoResponse): ResponseEntity<UpdateTodoResponse>{
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @DeleteMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할일을 삭제하는 메소드
    fun deleteTodo(@PathVariable todoId: Long){
        TODO("not implemented") //아직 기능 구현 안 함
    }
}