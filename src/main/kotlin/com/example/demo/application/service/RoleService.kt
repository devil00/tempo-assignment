package com.example.demo.application.service

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.application.port.`in`.RoleServicePort
import com.example.demo.application.port.out.RolePersistencePort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleService(@Autowired private val rolePersistencePort: RolePersistencePort): RoleServicePort  {
    private val logger = KotlinLogging.logger {}

    override fun createRole(roleDto: RoleDto): RoleDto {
        logger.info { "Creating role: ${roleDto}" }
        return  rolePersistencePort.save(roleDto)
    }

    override fun getRoleById(id: Int): RoleDto? {
        logger.info { "Getting role for id: $id" }
        return rolePersistencePort.findRoleById(id)
    }

    override fun deleteRoleById(id: Int) {
        rolePersistencePort.deleteById(id)
    }
}