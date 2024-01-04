package com.teamsparta.mytodolist.domain.authority.dto

data class TokenInfoDto(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String
)
