package com.example.demo.application.port.`in`

import com.example.demo.adapters.dto.UserDto

interface MemberServicePort {
    fun addRole(userId : String, roleId: Int) : UserDto
    fun getMemberById(userId: String): UserDto
    fun saveMember(userDto: UserDto): UserDto
}