package com.example.demo.adapters.mapper

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.application.domain.Role

class RoleMapper {
    companion object {
        fun toRoleDto(role : Role): RoleDto {
            return RoleDto(role.id, role.roleType, role.isDefault)
        }
        fun toRole(roleDto: RoleDto): Role {
            val roleId  = roleDto.id ?: 0;
            return Role(roleId, roleDto.roleType, roleDto.isDefault)
        }
    }
}