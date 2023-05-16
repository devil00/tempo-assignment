package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.RoleDto
import com.example.demo.adapters.dto.UpdateRoleRequest
import com.example.demo.application.domain.MemberShipKey
import com.example.demo.application.service.MemberShipService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/teams")
@Validated
/**
 * Resource to define the endpoints to manipulate team entity.
 */
class TeamController(val memberShipService: MemberShipService) {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/{teamId}/users/{userId}")
    @Operation(summary = "Assign a membership to user by linking to team and the role")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Successfully  created membership"), ApiResponse(responseCode = "500", description = "Not able create membership")])
    fun assignMemberShip(@PathVariable @Valid @NotBlank teamId: String, @PathVariable  @Valid @NotBlank userId: String, @Valid @RequestBody updateRoleRequest: UpdateRoleRequest): ResponseEntity<MemberShipDto> {
        var memberShip = memberShipService.addMemberShip(teamId, userId, updateRoleRequest)
        if(memberShip == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(memberShip, HttpStatus.CREATED)
    }

    @GetMapping("/{teamId}/users/{userId}")
    fun lookupRole(@PathVariable  @Valid @NotBlank teamId: String, @PathVariable  @Valid @NotBlank  userId: String): ResponseEntity<RoleDto>  {
        val memberShipKey = MemberShipKey(userId, teamId)
        val memberShipDto = memberShipService.findMemberShipByKey(memberShipKey)

        if (memberShipDto != null) {
            logger.warn { "Could not find membership for team: ${teamId}, userId: ${userId}" }
            return ResponseEntity(memberShipDto.roleDto, HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)

    }
}