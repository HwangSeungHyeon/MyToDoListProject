package com.teamsparta.mytodolist.domain.todo.controller

import org.springframework.web.bind.annotation.*

@RequestMapping("/todos") // base URI 경로 하위에 해당하는 건 전부 TodoController가 담당하게 됨
@RestController //TodoController를 Bean으로 등록
class TodoController {
    @GetMapping() //GET 메소드 핸들링, /todos에 접근한다
    //할일 리스트를 가져오는 메소드
    //Entity를 반환해야 함(Repository에 접근해야 하니까)
    fun getTodoList(){
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @GetMapping("/{todoId}") //GET 메소드 핸들링, /todos/{todoId}에 접근한다
    //할일 리스트를 가져오는 메소드
    //Entity를 반환해야 함(Repository에 접근해야 하니까)
    fun getTodo(){
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PostMapping() //POST 메소드 핸들링, /todos에 접근한다
    //사용자별 할일을 만드는 메소드
    //Entity를 반환해야 함(Repository에 접근해야 하니까)
    fun createTodo(){
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @PutMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할일을 수정하는 메소드
    //Entity를 반환해야 함(Repository에 접근해야 하니까)
    fun updateTodo(){
        TODO("not implemented") //아직 기능 구현 안 함
    }

    @DeleteMapping("/{todoId}") //GET 메소드 핸들링, /todos/todoId에 접근한다
    //선택한 할일을 삭제하는 메소드
    fun deleteTodo(){
        TODO("not implemented") //아직 기능 구현 안 함
    }
}