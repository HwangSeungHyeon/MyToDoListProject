package com.teamsparta.mytodolist.domain.todo.service

import com.teamsparta.mytodolist.domain.exception.ModelNotFoundException
import com.teamsparta.mytodolist.domain.todo.dto.*
import com.teamsparta.mytodolist.domain.todo.model.TodoModel
import com.teamsparta.mytodolist.domain.todo.model.toResponse
import com.teamsparta.mytodolist.domain.todo.model.toResponseWithComments
import com.teamsparta.mytodolist.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
* todoService 인터페이스 파일을 오버라이딩해서 구현
*/
@Service //TodoServiceImpl를 Service Layer를 담당하는 bean으로 설정
class TodoServiceImpl(
    private val todoRepository: TodoRepository //생성자를 이용한 의존성 주입(DI)
) : TodoService{

    //모든 할 일 목록을 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO 리스트로 바꾸고, Controller로 전달
    override fun getAllTodoList(getAllTodoListRequestWithNameDto: GetAllTodoListRequestWithNameDto): List<TodoResponseDto> {
        return if(getAllTodoListRequestWithNameDto.sortByDescend){ //작성일을 기준으로 내림차순일 경우
            todoRepository.findAllByNameOrderByDateDesc(getAllTodoListRequestWithNameDto.name).map { it.toResponse() }
        } else{ //작성일을 기준으로 오름차순일 경우
            todoRepository.findAllByNameOrderByDate(getAllTodoListRequestWithNameDto.name).map { it.toResponse() }
        }
    }

    //id에 해당하는 할 일 카드를 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO로 바꾸고, Controller로 전달
    override fun getTodoById(id: Long): TodoResponseWithCommentsDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)
        return todo.toResponseWithComments()
    }

    /*
    * 할 일 카드를 생성하는 메소드
    * Controller로부터 생성 요청(Request) DTO를 받는다.
    * 생성 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    @Transactional
    override fun createTodo(requestDto: CreateTodoRequestDto): TodoResponseDto {
        //작성한 제목이 1자 이상, 200자 이하인지 확인
        if(!textValidation(requestDto.title, 1)) throw IllegalStateException("Title must have between 1 and 200 words")

        //작성한 할 일 본문이 1자 이상, 1000자 이하인지 확인
        if(!textValidation(requestDto.description, 2)) throw IllegalStateException("Description must have between 1 and 1000 words")

        return todoRepository.save(
            TodoModel(
                title = requestDto.title,
                description = requestDto.description,
                date = LocalDateTime.now(), //date는 입력받지 않고, 그냥 timezone이 설정된 현재 시간을 넣음
                name = requestDto.name,
                status = false //status는 입력받지 않고, 그냥 false 값을 넣어줌
            )
        ).toResponse()
    }

    /*
    * id에 해당하는 할 일 카드를 수정하는 메소드
    * Controller로부터 수정 요청(Request) DTO를 받는다.
    * 수정 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    @Transactional
    override fun updateTodo(id: Long, requestDto: UpdateTodoRequestDto): TodoResponseDto {
        //수정할 제목이 1자 이상, 200자 이하인지 확인
        if(!textValidation(requestDto.title, 1)) throw IllegalStateException("Title must have between 1 and 200 words")

        //수정할 할 일 본문이 1자 이상, 1000자 이하인지 확인
        if(!textValidation(requestDto.description, 2)) throw IllegalStateException("Description must have between 1 and 1000 words")

        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)

        todo.name = requestDto.name
        todo.title = requestDto.title
        todo.description = requestDto.description

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun updateTodoStatus(id: Long, requestDto: UpdateTodoStatusRequestDto): TodoResponseDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)
        todo.status = requestDto.status
        return todoRepository.save(todo).toResponse()
    }

    //id에 해당하는 할 일 카드를 삭제하는 메소드
    //Entity로 DB에서 값을 가져와서 삭제한다.
    @Transactional
    override fun deleteTodo(id: Long){
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Todo", id)
        todoRepository.delete(todo)
    }
}

fun TodoService.textValidation(text: String, num: Int): Boolean{
    return when (num) {
        1 -> text.length in (1..200) //제목은 1자 이상, 200자 이내
        2 -> text.length in (1..1000) //본문은 1자 이상, 1000자 이내
        else -> false
    }
}