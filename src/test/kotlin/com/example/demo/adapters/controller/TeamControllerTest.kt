package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.ErrorMessageModel
import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.dto.UpdateRoleRequest
import com.example.demo.application.service.MemberShipService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(TeamController::class)
@AutoConfigureWebTestClient
internal class TeamControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var memberShipService: MemberShipService

    @Test
    fun assignMemberShip() {
        //Given
        val roleDto = RoleDto(2, "Developer", false)
        val memberShipDto = MemberShipDto("123", "456", roleDto)
        val updateRoleRequest = UpdateRoleRequest()
        updateRoleRequest.roleId = 2

        every { memberShipService.addMemberShip(any(), any(), any())} returns memberShipDto

        //When
        val savedMemberShipDto = webTestClient
            .post()
            .uri("/teams/456/users/123")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updateRoleRequest)
            .exchange()
            .expectBody(MemberShipDto::class.java)
            .returnResult().responseBody

        //Then
        assertEquals("123", savedMemberShipDto.userId)
        assertEquals("456", savedMemberShipDto.teamId)
        assertEquals(2, savedMemberShipDto.roleDto.id)
        assertEquals("Developer", savedMemberShipDto.roleDto.roleType)
    }


    @Test
    fun assignMemberShip_ValidationFailure() {
        //Given
        val roleDto = RoleDto(2, "Developer", false)
        val memberShipDto = MemberShipDto("123", "456", roleDto)
        val updateRoleRequest = UpdateRoleRequest()

        every { memberShipService.addMemberShip(any(), any(), any())} returns memberShipDto

        //When
        val response = webTestClient
            .post()
            .uri("/teams/456/users/123")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updateRoleRequest)
            .exchange()
            .expectBody(ErrorMessageModel::class.java)
            .returnResult().responseBody

        //Then
        assertEquals("must be greater than or equal to 1", response.message)
    }

    @Test
    fun lookupRole() {
        //Given
        val roleDto = RoleDto(2, "Developer", false)
        val memberShipDto = MemberShipDto("123", "456", roleDto)

        every { memberShipService.findMemberShipByKey(any())} returns memberShipDto

        //When
        val response = webTestClient
            .get()
            .uri("/teams/456/users/123")
            .exchange()
            .expectBody(RoleDto::class.java)
            .returnResult().responseBody

        //Then
        assertEquals(2, response.id)
        assertEquals("Developer", response.roleType)
    }
}