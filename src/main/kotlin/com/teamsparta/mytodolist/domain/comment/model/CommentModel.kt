package com.teamsparta.mytodolist.domain.comment.model

import com.teamsparta.mytodolist.domain.comment.dto.CommentResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime


//Domain Model: Domain Service, Entity, VO(Value Object)를 포함하는 개념

@Entity //Entity annotation, 객체(class)를 entity로 사용하기 위해서 사용
@Table(name = "comment") //매핑할 테이블 이름을 정의
class CommentModel private constructor(
    @Column(name = "content") //매핑할 테이블의 컬럼을 정의
    var content: String, //댓글 내용은 수정 가능, null 허용 X

    @Column(name = "date") //매핑할 테이블의 컬럼을 정의
    val date: LocalDateTime, //날짜는 수정 불가능, null 허용 X

    @Column(name = "name") //매핑할 테이블의 컬럼을 정의
    val name: String, //작성자 이름은 수정 불가능, null 허용 X

    @Column(name = "password")
    val password: String, //비밀번호는 수정 불가능, null 허용 X

    @Column(name = "todo_id")
    val todoId: Long

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 ID를 자동으로 생성
    var id:Long? = null //DB에서 ID를 만들기 때문에 var 키워드를 사용

    companion object{
        fun create(
            content: String,
            date: LocalDateTime,
            name: String,
            password: String,
            todoId: Long
        ): CommentModel{
            return CommentModel(content, date, name, password, todoId)
        }

        fun toResponse(
            commentModel: CommentModel
        ): CommentResponseDto{
            return CommentResponseDto(
                id = commentModel.id!!,
                content = commentModel.content,
                name = commentModel.name,
                date = commentModel.date
            )
        }
    }
}