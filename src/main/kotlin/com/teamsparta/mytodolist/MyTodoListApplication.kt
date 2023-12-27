package com.teamsparta.mytodolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyTodoListApplication

fun main(args: Array<String>) {
    runApplication<MyTodoListApplication>(*args)
}
