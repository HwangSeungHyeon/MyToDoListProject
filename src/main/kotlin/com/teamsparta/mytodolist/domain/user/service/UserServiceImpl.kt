package com.teamsparta.mytodolist.domain.user.service

import com.teamsparta.mytodolist.domain.authority.common.TokenProvider
import com.teamsparta.mytodolist.domain.authority.dto.TokenInfoDto
import com.teamsparta.mytodolist.domain.exception.LoginFailedException
import com.teamsparta.mytodolist.domain.user.dto.LoginRequestDto
import com.teamsparta.mytodolist.domain.user.dto.SignUpRequestDto
import com.teamsparta.mytodolist.domain.user.dto.UserResponseDto
import com.teamsparta.mytodolist.domain.user.model.UserEntity
import com.teamsparta.mytodolist.domain.user.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
* UserService 인터페이스 파일을 오버라이딩해서 구현
*/
@Service //UserServiceImpl를 Service Layer를 담당하는 bean으로 설정
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
): UserService{

    //회원가입을 담당하는 메소드
    //Controller로부터 회원가입 정보가 담긴 요청(Request) DTO를 받는다.
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO 리스트로 바꾸고, Controller로 전달
    @Transactional
    override fun signUp(
        signUpRequestDto: SignUpRequestDto
    ): UserResponseDto {
        if(userRepository.existsByEmail(signUpRequestDto.email)) throw IllegalStateException("Email is already in app_user DB")
        return UserEntity.toResponse(userRepository.save(UserEntity.create(signUpRequestDto, encoder)))
    }


    //로그인을 담당하는 메소드
    //controller로부터 로그인 정보가 담긴 요청(Request) DTO를 받는다.
    //로그인 요청(Request) DTO에 있는 값으로 토큰이 들어있는 TokenInfoDto 생성
    //그 후 TokenInfoDto를 Controller에 전달
    override fun login(loginRequestDto: LoginRequestDto): TokenInfoDto {
        //이메일을 기준으로 데이터베이스에서 정보 가져옴
        val myUser = userRepository.findByEmail(loginRequestDto.email) ?: throw LoginFailedException("Not registerd!")

        //비밀번호 일치하는지 확인
         if(!encoder.matches(loginRequestDto.password, myUser.password)) throw LoginFailedException("ID or Password is incorrect!")

        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequestDto.email, loginRequestDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        val token = tokenProvider.createToken(authentication)
//        val token = tokenProvider.createToken("permitAll")
        return token
    }
}