package com.teamsparta.mytodolist.domain.todo.repository

import com.teamsparta.mytodolist.domain.todo.model.TodoModel
import org.springframework.data.jpa.repository.JpaRepository

/*
* Spring Layer의 일부
* Repository layer: 가장 하위에 존재하는 Layer
* 데이터베이스와 통신하는 역할 담당 (값 수정, 값 추가, 삭제, 값 가져오기 등)
* Java 및 kotlin 객체를 사용해서 DB에 대해 CRUD를 쉽게 할 수 있도록 하는 JPA를 사용해서 DB와 통신
*/
interface TodoRepository: JpaRepository<TodoModel, Long> {

    //이름이 일치하는 컬럼을 내림차순으로 리스트에 넣음
    fun findAllByNameOrderByDateDesc(name: String): List<TodoModel>

    //이름이 일치하는 컬럼을 오름차순으로 리스트에 넣음
    fun findAllByNameOrderByDate(name: String): List<TodoModel>
}