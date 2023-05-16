package com.example.demo.application.service

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.dto.UserDto
import com.example.demo.application.port.`in`.MemberServicePort
import com.example.demo.application.port.out.MemberPersistencePort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service responsible for defining operations for the user entity
 */
@Service
class MemberService(@Autowired private val memberPersistencePort: MemberPersistencePort, @Autowired private val roleService: RoleService) : MemberServicePort {
    private val logger = KotlinLogging.logger {}

    @Transactional
    /** Add  the given role to the useridentified by the user-id.
     * @param teamId
     * @param userId
     * @return {@link MemberShipDto}
     */
    override fun addRole(userId: String, roleId: Int): UserDto {
        logger.info { "Adding roleId: ${roleId} to user: ${userId}" }
        val member = getMemberById(userId)
        val role = roleService.getRoleById(roleId)

        member.roles = listOf(role) as List<RoleDto>

        memberPersistencePort.saveMember(member)

        logger.info { "Successfully Added role ${role} on user id = ${userId}"}
        return member
    }

    override fun getMemberById(userId: String): UserDto {
        return memberPersistencePort.getMemberById(userId)
    }

    override fun saveMember(userDto: UserDto): UserDto {
        return memberPersistencePort.saveMember(userDto)
    }
}