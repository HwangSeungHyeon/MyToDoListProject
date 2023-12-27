package com.teamsparta.mytodolist.domain.exception

import com.teamsparta.mytodolist.domain.exception.dto.ErrorResponseDto
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ErrorResponseDto{
        return ErrorResponseDto(e.message)
    }
}