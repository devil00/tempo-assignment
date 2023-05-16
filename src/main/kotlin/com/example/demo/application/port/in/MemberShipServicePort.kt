package com.example.demo.application.port.`in`

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.UpdateRoleRequest
import com.example.demo.application.domain.MemberShipKey

interface MemberShipServicePort {
    fun findMemberShipByKey(key: MemberShipKey): MemberShipDto?
    fun findMemberShipByRole(roleId: Int): MemberShipDto?
    fun addMemberShip(teamId: String, userId: String, updateRoleRequest: UpdateRoleRequest): MemberShipDto?
    fun deleteMemberShipByKey(key: MemberShipKey)
}