package com.teamsparta.mytodolist.domain.todo.model

import com.teamsparta.mytodolist.domain.comment.model.CommentModel
import com.teamsparta.mytodolist.domain.todo.dto.CreateTodoRequestDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseWithCommentsDto
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

//Domain Model: Domain Service, Entity, VO(Value Object)를 포함하는 개념

@Entity //Entity annotation, 객체(class)를 entity로 사용하기 위해서 사용
@Table(name="todo") //매핑할 테이블 이름을 정의
class TodoModel private constructor( //데이터베이스에서 데이터를 가져올 때 사용하는 클래스
    @Column(name = "title") //매핑할 테이블의 컬럼을 정의
    var title: String, //제목은 수정 가능, null 허용 X

    @Column(name = "description") //매핑할 테이블의 컬럼을 정의
    var description: String, //내용은 수정 가능, null 허용 X

    @Column(name="date") //매핑할 테이블의 컬럼을 정의
    val date: LocalDateTime, //작성 날짜는 수정 불가능

    @Column(name = "name") //매핑할 테이블의 컬럼을 정의
    var name: String, //작성자 이름은 수정 가능, null 허용 X

    @Column(name = "status")
    var status: Boolean,

    @BatchSize(size = 10) //쿼리 batch size를 설정해서 1+n 쿼리 문제를 해결
    @OneToMany(mappedBy = "todoId", fetch = FetchType.LAZY, cascade=[CascadeType.ALL], orphanRemoval = true)
    var comments:MutableList<CommentModel> = mutableListOf()
) {
    @Id //PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 ID를 자동으로 생성
    var id: Long? = null //DB에서 ID를 만들기 때문에 var 키워드를 사용

    companion object{ // companion object를 활용한 객체 생성
        fun create(
            requestDto: CreateTodoRequestDto
        ): TodoModel{
            return TodoModel(
                title = requestDto.title,
                description = requestDto.description,
                date = LocalDateTime.now(), //date는 입력받지 않고, 그냥 timezone이 설정된 현재 시간을 넣음
                name = requestDto.name,
                status = false //status는 입력받지 않고, 그냥 false 값을 넣어줌
            )
        }

        /*
        * DB에서 가져온 값이 담긴 Entity를 응답(response) DTO로 바꾸기 위한 메소드
        * DTO: 각 Layer 사이의 데이터를 전달하는데 사용
        * 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
        */
        fun toResponse(
            todoModel: TodoModel
        ): TodoResponseDto{
            return TodoResponseDto(
                id = todoModel.id!!,
                title = todoModel.title,
                description = todoModel.description,
                date = todoModel.date,
                name = todoModel.name,
                status = todoModel.status)
        }

        fun toResponseWithComments( // companion object를 활용한 객체 변환
            todoModel: TodoModel
        ): TodoResponseWithCommentsDto{
            return TodoResponseWithCommentsDto(
                id = todoModel.id!!,
                title = todoModel.title,
                description = todoModel.description,
                date = todoModel.date,
                name = todoModel.name,
                status = todoModel.status,
                comments = todoModel.comments)
        }
    }
}