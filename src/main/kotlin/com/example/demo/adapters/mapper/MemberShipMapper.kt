package com.example.demo.adapters.mapper

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.RoleDto
import com.example.demo.application.domain.MemberShip
import com.example.demo.application.domain.MemberShipKey
import com.example.demo.application.domain.Role

//@Mapper
class MemberShipMapper {
    companion object {
        fun toMemberShipDto(memberShip: MemberShip): MemberShipDto {
            return MemberShipDto(memberShip.id.userId, memberShip.id.teamId, RoleDto(memberShip.role.id, memberShip.role.roleType, memberShip.role.isDefault))
        }
        fun toMemberShip(memberShipDto: MemberShipDto) : MemberShip {
            var role: Role? = null
            if(memberShipDto.roleDto != null) {
                role = memberShipDto.roleDto.id?.let { Role(it, memberShipDto.roleDto.roleType, memberShipDto.roleDto.isDefault) }
            }

            return MemberShip(MemberShipKey(memberShipDto.userId, memberShipDto.teamId), role!!)
        }
    }
}