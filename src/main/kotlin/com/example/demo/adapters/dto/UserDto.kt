package com.example.demo.adapters.dto

data class UserDto(
    var userId: String,
    var roles: List<RoleDto> = emptyList()
)
