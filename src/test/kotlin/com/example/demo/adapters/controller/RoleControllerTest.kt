package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.ErrorMessageModel
import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.RoleDto
import com.example.demo.application.service.MemberShipService
import com.example.demo.application.service.RoleService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient


@WebMvcTest(RoleController::class)
@AutoConfigureWebTestClient
class RoleControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var memberShipService: MemberShipService

    @MockkBean
    lateinit var roleService: RoleService

    @Test
    fun createRole() {
        //Given
        val roleDto = RoleDto(null, "Product Owner", false)
        val roleDtoSaved = RoleDto(1, "Product Owner", false)

        every { roleService.createRole(any())} returns roleDtoSaved

        //When
        val savedRoleDTO = webTestClient
            .post()
            .uri("/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(roleDto)
            .exchange().expectStatus().isCreated
            .expectBody(RoleDto::class.java)
            .returnResult().responseBody

        //Then
        assertTrue(savedRoleDTO.id != null)
    }

    @Test
    fun createRole_ValidationFailure() {
        //Given
        val roleDto = RoleDto(null, "", false)
        val roleDtoSaved = RoleDto(1, "Product Owner", false)

        every { roleService.createRole(any())} returns roleDtoSaved

        //When
        val response = webTestClient
            .post()
            .uri("/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(roleDto)
            .exchange().expectStatus().isBadRequest
            .expectBody(ErrorMessageModel::class.java)
            .returnResult().responseBody
        println("=========> ${response}")

        //Then
        assertEquals("Role name should not be blank", response.message)
    }

//    @Test
    fun createRole_Failure() {
        //Given
        val roleDto = RoleDto(null, "QA", false)
        val roleDtoSaved = RoleDto(1, "Product Owner", false)

        every { roleService.createRole(any())} throws RuntimeException("Unexpected error")

        //When
        val savedRoleDTO = webTestClient
            .post()
            .uri("/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(roleDto)
            .exchange().expectStatus().is5xxServerError
            .expectBody()
            .returnResult()
            .responseBody
        //Then

    }

    @Test
    fun getRole() {
        //Given
        val roleDto = RoleDto(null, "Product Owner", false)
        val roleDtoSaved = RoleDto(1, "Product Owner", false)

        every { roleService.getRoleById(any())} returns roleDtoSaved

        //When
        val savedRoleDTO = webTestClient
            .get()
            .uri("/roles/1")
            .exchange()
            .expectBody(RoleDto::class.java)
            .returnResult().responseBody

        //Then
        assertTrue(savedRoleDTO.id == 1)
        assertEquals("Product Owner", savedRoleDTO.roleType)
    }

    @Test
    fun findMembership() {
        //Given
        val roleDto = RoleDto(1, "Product Owner", false)
        val memberShipDto = MemberShipDto("123", "456", roleDto)

        every { memberShipService.findMemberShipByRole(any())} returns memberShipDto

        //When
        val savedMemberShipDto = webTestClient
            .get()
            .uri("/roles/1/membership")
            .exchange()
            .expectBody(MemberShipDto::class.java)
            .returnResult().responseBody

        //Then
        assertEquals("123", savedMemberShipDto.userId)
        assertEquals("456", savedMemberShipDto.teamId)
        assertEquals(1, savedMemberShipDto.roleDto.id)
        assertEquals("Product Owner", savedMemberShipDto.roleDto.roleType)
    }
}