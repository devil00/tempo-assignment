package com.example.demo.application.port.out

import com.example.demo.adapters.dto.UserDto

interface MemberPersistencePort {
    fun saveMember(user: UserDto): UserDto
    fun getMemberById(userId: String): UserDto
}