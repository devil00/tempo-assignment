package com.example.demo.application.service

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.dto.UserDto
import com.example.demo.application.port.out.MemberPersistencePort
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(MemberService::class)
@AutoConfigureWebTestClient
internal class MemberServiceTest {
    @MockkBean
    lateinit var memberPersistencePort: MemberPersistencePort

    @MockkBean
    lateinit var roleService: RoleService

    val roleDtoSaved = RoleDto(2, "Dev", true)
    val memberDto = UserDto("1", listOf(roleDtoSaved))

    @BeforeEach
    fun setUp() {
        val memberDto = UserDto("1", emptyList())
    }

    @Test
    fun addRole() {
        every { memberPersistencePort.getMemberById(any())} returns memberDto
        every { memberPersistencePort.saveMember(any()) } returns memberDto
        every { roleService.getRoleById(any()) } returns roleDtoSaved

        var memberService = MemberService(memberPersistencePort, roleService)

        val result =  memberService.addRole("1", 2)

        assertEquals("1", result.userId)
        assertEquals(1, result.roles.size)
        assertEquals("Dev", result.roles.get(0).roleType)

    }

}