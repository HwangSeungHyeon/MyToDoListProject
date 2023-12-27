package com.teamsparta.mytodolist.domain.todo.model

import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime

//Domain Model: Domain Service, Entity, VO(Value Object)를 포함하는 개념

@Entity //Entity annotation, 객체(class)를 entity로 사용하기 위해서 사용
@Table(name="todo") //매핑할 테이블 이름을 정의
class TodoModel( //데이터베이스에서 데이터를 가져올 때 사용하는 클래스
    @Column(name = "title") //매핑할 테이블의 컬럼을 정의
    var title: String, //제목은 수정 가능, null 허용 X

    @Column(name = "description") //매핑할 테이블의 컬럼을 정의
    var description: String, //내용은 수정 가능, null 허용 X

    @Column(name="date") //매핑할 테이블의 컬럼을 정의
    val date: LocalDateTime, //작성 날짜는 수정 불가능

    @Column(name = "name") //매핑할 테이블의 컬럼을 정의
    var name: String //작성자 이름은 수정 가능, null 허용 X
) {
    @Id //PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 ID를 자동으로 생성
    var id: Long? = null //DB에서 ID를 만들기 때문에 var 키워드를 사용
}

/*
* DB에서 가져온 값이 담긴 Entity를 응답(response) DTO로 바꾸기 위한 메소드
* DTO: 각 Layer 사이의 데이터를 전달하는데 사용
* 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
*/
fun TodoModel.toResponse(): TodoResponseDto{
    return TodoResponseDto(
        id = id!!,
        title = title,
        description = description,
        date = date,
        name = name
    )
}