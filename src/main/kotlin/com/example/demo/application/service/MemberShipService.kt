package com.example.demo.application.service

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.UpdateRoleRequest
import com.example.demo.application.domain.MemberShipKey
import com.example.demo.application.port.`in`.MemberShipServicePort
import com.example.demo.application.port.out.MemberShipPersistencePort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberShipService(@Autowired private val memberShipPersistencePort: MemberShipPersistencePort) : MemberShipServicePort {
    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var roleService: RoleService

    override fun findMemberShipByKey(key: MemberShipKey): MemberShipDto? {
        return memberShipPersistencePort.findMemberShipByKey(key)
    }

    override fun findMemberShipByRole(roleId: Int): MemberShipDto? {
        return memberShipPersistencePort.findMemberShipByRole(roleId)
    }

    @Transactional
    /** Create memmbership by assigning role to the user and allocating the user to the given team identified by the team-id.
     * @param teamId
     * @param userId
     * @param updateRoleRequest instance of {@link UpdateRoleRequest}
     * @return {@link MemberShipDto}
     */
    override fun addMemberShip(teamId: String, userId: String, updateRoleRequest: UpdateRoleRequest): MemberShipDto? {
        logger.info { "Assigning membership for teamId {$teamId}, userId: ${userId} , roleId: ${updateRoleRequest.roleId}" }
        var roleDto = roleService.getRoleById(updateRoleRequest.roleId)
        logger.info { "Found role  ${roleDto} for the id: ${updateRoleRequest.roleId}" }
        return roleDto?.let { val memberShipDto = MemberShipDto(userId,teamId, it)
            memberShipPersistencePort.addMemberShip(memberShipDto)
        }
    }

    override fun deleteMemberShipByKey(key: MemberShipKey) {
        memberShipPersistencePort.deleteMemberShipByKey(key)
    }

}