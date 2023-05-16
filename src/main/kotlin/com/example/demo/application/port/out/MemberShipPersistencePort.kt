package com.example.demo.application.port.out

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.application.domain.MemberShipKey

interface MemberShipPersistencePort {
    fun findMemberShipByKey(key: MemberShipKey): MemberShipDto?
    fun findMemberShipByRole(roleId: Int): MemberShipDto?
    fun addMemberShip(memberShipDto: MemberShipDto): MemberShipDto
    fun deleteMemberShipByKey(key: MemberShipKey)
}