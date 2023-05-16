package com.example.demo.application.port.`in`

import com.example.demo.adapters.dto.RoleDto

interface RoleServicePort {
    fun createRole(roleDto: RoleDto) : RoleDto
    fun getRoleById(id: Int) : RoleDto?
    fun deleteRoleById(id: Int)
}