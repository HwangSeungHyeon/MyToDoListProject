package com.teamsparta.mytodolist.domain.todo.repository

import com.teamsparta.mytodolist.domain.todo.model.TodoModel
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/*
* Spring Layer의 일부
* Repository layer: 가장 하위에 존재하는 Layer
* 데이터베이스와 통신하는 역할 담당 (값 수정, 값 추가, 삭제, 값 가져오기 등)
* Java 및 kotlin 객체를 사용해서 DB에 대해 CRUD를 쉽게 할 수 있도록 하는 JPA를 사용해서 DB와 통신
*/
interface TodoRepository: JpaRepository<TodoModel, Long> {

    //이름이 일치하는 컬럼을 내림차순으로 리스트에 넣음
//    fun findAllByNameOrderByDateDesc(name: String, pageable: Pageable): List<TodoModel>

    //n+1 쿼리 문제를 해결하기 위해서 Fetch Join을 사용 (Left Outer Join을 수행함)
    //JPQL을 사용하여 DB에서 데이터를 가져올 때 처음부터 연관된 데이터까지 같이 가져오게 하는 방법
    //pagination을 못함
//    @Query("select t from TodoModel t left join fetch t.comments where t.name = :name order by t.date desc")
//    fun findAllByNameOrderByDateDesc(@Param("name") name: String, pageable: Pageable): List<TodoModel>

    //이름이 일치하는 컬럼을 오름차순으로 리스트에 넣음
//    fun findAllByNameOrderByDate(name: String, pageable: Pageable): List<TodoModel>

    //n+1 쿼리 문제를 해결하기 위해서 Fetch Join을 사용 (Left Outer Join을 수행함)
    //JPQL을 사용하여 DB에서 데이터를 가져올 때 처음부터 연관된 데이터까지 같이 가져오게 하는 방법
    //pagination을 못함
//    @Query("select t from TodoModel t left join fetch t.comments where t.name = :name order by t.date")
//    fun findAllByNameOrderByDate(@Param("name") name: String, pageable: Pageable): List<TodoModel>


    //n+1 쿼리 문제를 해결하기 위해서 Fetch Join을 사용 (Left Outer Join을 수행함)
    //정렬을 pageable에서 담당해주니까 코드를 하나로 줄일 수 있음
    @Query("select t from TodoModel t left join fetch t.comments where t.name = :name")
    fun findAllByName(@Param("name") name: String, pageable: Pageable): List<TodoModel>
}