package com.example.demo.adapters.persistence

import com.example.demo.adapters.dto.UserDto
import com.example.demo.adapters.mapper.MemberMapper
import com.example.demo.application.port.out.MemberPersistencePort
import com.example.demo.application.service.RoleService
import com.example.demo.common.exceptions.MemberNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberPersistenceJpaAdapter(@Autowired private val memberRepository: MemberRepository,
                                  @Autowired roleService: RoleService) : MemberPersistencePort {

    override fun saveMember(user: UserDto): UserDto {
        return MemberMapper.toMemberDto(memberRepository.save(MemberMapper.toMember(user)))
    }

    override fun getMemberById(userId: String): UserDto {
        val user = memberRepository.findById(userId).orElseThrow { MemberNotFoundException("Member with id ${userId} is not found") }
        return MemberMapper.toMemberDto(user)
    }

}