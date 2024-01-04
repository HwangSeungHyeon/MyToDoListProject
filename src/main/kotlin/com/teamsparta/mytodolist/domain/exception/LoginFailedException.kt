package com.teamsparta.mytodolist.domain.exception

data class LoginFailedException(
    private val text: String
): RuntimeException(text)