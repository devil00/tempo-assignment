package com.example.demo.application.port.out

import com.example.demo.adapters.dto.RoleDto

interface RolePersistencePort {
    fun findRoleById(id: Int): RoleDto?
    fun save(role: RoleDto): RoleDto
    fun deleteById(roleId: Int)
}