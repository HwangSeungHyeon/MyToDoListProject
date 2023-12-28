package com.teamsparta.mytodolist.domain.todo.controller

import com.teamsparta.mytodolist.domain.todo.dto.*
import com.teamsparta.mytodolist.domain.todo.service.TodoService
import org.springframework.web.bind.annotation.*

/*
* Spring의 Web Layer의 일부
* Spring의 Service 레이어와 접근하기 위해서 사용하는 클래스
* Client의 요청(Request)을 받고, 응답(Response)을 주는 역할
*/
@RequestMapping("/todos") // base URI 경로 하위에 해당하는 건 전부 TodoController가 담당하게 됨
@RestController //TodoController를 Controll layer를 담당하는 Bean으로 등록
class TodoController(
    private val todoService: TodoService //생성자를 이용한 의존성 주입(DI)
) {
    @GetMapping() //GET 메소드 핸들링, /todos에 접근한다
    //할 일 목록 리스트를 가져오는 메소드
    //TodoResponseDto 리스트를 반환
    fun getTodoList(): List<TodoResponseDto> {
        return todoService.getAllTodoList()
    }

    @GetMapping("/{todoId}") //GET 메소드 핸들링, /todos/{todoId}에 접근한다
    //선택한 할 일 카드를 가져오는 메소드
    //todoId를 argument로 받아서 TodoResponseDto를 반환
    fun getTodo(@PathVariable todoId: Long): TodoResponseWithCommentsDto {
        return todoService.getTodoById(todoId)
    }

    @PostMapping() //POST 메소드 핸들링, /todos에 접근한다
    //할 일 카드를 만드는 메소드
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequestDto): TodoResponseDto{
        //CreateTodoRequest DTO를 argument로 받아서 TodoResponseDto를 반환
        return todoService.createTodo(createTodoRequest)
    }

    @PutMapping("/{todoId}") //PUT 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할 일 카드를 수정하는 메소드
    //todoId와 UpdateTodoRequest DTO를 argument로 받아서 TodoResponseDto를 반환
    fun updateTodo(@PathVariable todoId: Long, @RequestBody updateTodoRequest: UpdateTodoRequestDto): TodoResponseDto{
        return todoService.updateTodo(todoId, updateTodoRequest)
    }

    @PatchMapping("{todoId}") //PATCH 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할 일 카드를 완료 상태로 바꾸는 메소드
    //todoId와 UpdateTodoStatusRequest DTO를 argument로 받아서 TodoResponseDto를 반환
    fun updateTodoStatus(@PathVariable todoId: Long, updateTodoStatusRequestDto: UpdateTodoStatusRequestDto): TodoResponseDto{
        return todoService.updateTodoStatus(todoId, updateTodoStatusRequestDto)
    }

    @DeleteMapping("/{todoId}") //DELETE 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할 일을 삭제하는 메소드
    //만약 선택된 할 일에 댓글이 적혀있다면 댓글도 전부 삭제
    fun deleteTodo(@PathVariable todoId: Long){
        return todoService.deleteTodo(todoId)
    }
}