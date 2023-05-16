package com.example.demo.adapters.mapper

import com.example.demo.adapters.dto.UserDto
import com.example.demo.application.domain.Role
import com.example.demo.application.domain.User
import java.util.stream.Collectors

//@Mapper(componentModel = "spring", uses = [RoleMapper::class])
class MemberMapper {
    companion object {
        fun toMemberDto(user: User): UserDto {
            val roles = user.roles.stream().map { RoleMapper.toRoleDto(it) }.collect(Collectors.toList())
            return UserDto(user.id, roles)
        }

        fun toMember(userDto: UserDto): User {
            val roles: List<Role> = userDto.roles.map { RoleMapper.toRole(it) }
            val user = User()
            user.id = userDto.userId
            user.roles = roles
            return user
        }
    }
}