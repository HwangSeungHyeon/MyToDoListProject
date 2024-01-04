package com.teamsparta.mytodolist.domain.user.model

import com.teamsparta.mytodolist.domain.user.dto.SignUpRequestDto
import com.teamsparta.mytodolist.domain.user.dto.UserResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.password.PasswordEncoder

//Domain Model: Domain Service, Entity, VO(Value Object)를 포함하는 개념

@Entity //Entity annotation, 객체(class)를 entity로 사용하기 위해서 사용
@Table(name = "app_user") //매핑할 테이블 이름을 정의
class UserEntity private constructor( //데이터베이스에서 데이터를 가져올 때 사용하는 클래스
    @Column(name = "name") //매핑할 테이블의 컬럼을 정의
    val name: String, //이름은 수정 불가능, null 허용 X

    @Column(name = "email") //매핑할 테이블의 컬럼을 정의
    val email: String, //이메일은 수정 불가능, null 허용

    @Column(name = "password") //매핑할 테이블의 컬럼을 정의
    var password: String //비밀번호는 수정 불가능, null 허용 X
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null //DB에서 ID를 만들기 때문에 var 키워드를 사용

    companion object{ // companion object를 활용한 객체 변환

        fun create(
            requestDto: SignUpRequestDto,
            encoder: PasswordEncoder
        ): UserEntity{
            return UserEntity(
                name = requestDto.name,
                email = requestDto.email,
                password = encoder.encode(requestDto.password) //비밀번호를 PasswordEncoder를 이용해서 암호화
            )
        }

        /*
         * DB에서 가져온 값이 담긴 Entity를 응답(response) DTO로 바꾸기 위한 메소드
         * DTO: 각 Layer 사이의 데이터를 전달하는데 사용
         * 응답(Request)과 요청(Response) 또한 DTO로 표현 가능
         */
        fun toResponse(
            userEntity: UserEntity
        ): UserResponseDto{
            return UserResponseDto(
                id = userEntity.id!!,
                name = userEntity.name,
                email = userEntity.email
            )
        }
    }
}