package com.teamsparta.mytodolist.domain.todo.model

import com.teamsparta.mytodolist.domain.todo.dto.TodoResponseDto
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name="todo") // 매핑할 테이블 이름을 정의
class TodoModel(
    @Column(name = "title") //매핑할 테이블의 컬럼을 정의
    var title: String, //제목은 수정 가능, null 허용 X

    @Column(name = "description") //매핑할 테이블의 컬럼을 정의
    var description: String, //내용은 수정 가능, null 허용 X

    @Column(name="date") //매핑할 테이블의 컬럼을 정의
    val date: Date, //작성 날짜는 수정 불가능

    @Column(name = "name") //매핑할 테이블의 컬럼을 정의
    var name: String //작성자 이름은 수정 가능, null 허용 X
) {
    @Id //PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 ID를 자동으로 생성
    var id: Long? = null //DB에서 ID를 만들기 때문에 var 키워드를 사용
}

fun TodoModel.toResponse(): TodoResponseDto{
    return TodoResponseDto(
        id = id!!,
        title = title,
        description = description,
        date = date,
        name = name
    )
}