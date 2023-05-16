package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.dto.UserDto
import com.example.demo.application.service.MemberService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(MemberController::class)
@AutoConfigureWebTestClient
class MemberControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var memberService: MemberService

    @Test
    fun createRole() {
        //Given
        val roleDto = RoleDto(null, "Product Owner", false)
        val memberDto = UserDto("1", emptyList())

        every { memberService.addRole(any(), any())} returns memberDto

        //When
        val savedUser = webTestClient
            .post()
            .uri("/users/1a/roles/2")
            .contentType(MediaType.APPLICATION_JSON)
            .exchange().expectStatus().isCreated
            .expectBody(UserDto::class.java)
            .returnResult().responseBody

        //Then
        assertEquals("1a", savedUser.userId)
    }
}