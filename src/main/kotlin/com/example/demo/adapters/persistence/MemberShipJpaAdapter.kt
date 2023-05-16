package com.example.demo.adapters.persistence

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.mapper.MemberShipMapper
import com.example.demo.application.domain.MemberShipKey
import com.example.demo.application.port.out.MemberShipPersistencePort
import com.example.demo.common.exceptions.MemberShipNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberShipJpaAdapter(@Autowired private val memberShipRepository: MemberShipRepository) : MemberShipPersistencePort {
//    private val memberShipMapper: MemberShipMapper = Mappers.getMapper(MemberShipMapper::class.java)

    override fun findMemberShipByKey(key: MemberShipKey): MemberShipDto? {
        val memberShip = memberShipRepository.findById(key).orElseThrow { MemberShipNotFoundException("Not found membership for key: ${key}") }
        return MemberShipMapper.toMemberShipDto(memberShip)
    }

    override fun findMemberShipByRole(roleId: Int): MemberShipDto? {
        val memberShip = memberShipRepository.findMemberShipByRole(roleId)
            ?: throw MemberShipNotFoundException("Not found membership for role: ${roleId}")
        return MemberShipMapper.toMemberShipDto(memberShip)
    }

    override fun addMemberShip(memberShipDto: MemberShipDto): MemberShipDto {
        val memberShip = MemberShipMapper.toMemberShip(memberShipDto)
        val memberShipSaved = memberShipRepository.save(memberShip)
        return MemberShipMapper.toMemberShipDto(memberShipSaved)
    }

    override fun deleteMemberShipByKey(key: MemberShipKey) {
        memberShipRepository.deleteById(key)
    }
}