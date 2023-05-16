package com.example.demo.adapters.persistence

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.mapper.RoleMapper
import com.example.demo.application.port.out.RolePersistencePort
import com.example.demo.common.exceptions.RoleNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RolePersistenceJpaAdapter(@Autowired private val roleRepository: RoleRepository) : RolePersistencePort {
//    private val roleMapper: RoleMapper = Mappers.getMapper(RoleMapper::class.java)

    override fun findRoleById(id: Int): RoleDto? {
        val role = roleRepository.findById(id).orElseThrow { RoleNotFoundException("Role with id ${id} is not found") }
        return RoleMapper.toRoleDto(role)
    }

    override fun save(roleDto: RoleDto): RoleDto {
        val role = RoleMapper.toRole(roleDto)
        val roleSaved = roleRepository.save(role)
        return RoleMapper.toRoleDto(roleSaved)
    }

    override fun deleteById(roleId: Int) {
        roleRepository.deleteById(roleId)
    }

}