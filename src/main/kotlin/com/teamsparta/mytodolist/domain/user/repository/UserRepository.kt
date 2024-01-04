package com.teamsparta.mytodolist.domain.user.repository

import com.teamsparta.mytodolist.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>{
    fun existsByEmail(email: String): Boolean //DB에 이메일이 존재하는지 확인하는 메소드

    fun findByEmail(email: String): UserEntity? //DB에 있는 유저 정보 가져오는 Entity
}