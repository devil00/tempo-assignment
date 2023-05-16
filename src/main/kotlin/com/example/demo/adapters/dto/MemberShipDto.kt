package com.example.demo.adapters.dto

import javax.validation.constraints.NotBlank


data class MemberShipDto (
    @field:NotBlank(message = "User Id should not be empty")
    var userId: String,
    @field:NotBlank(message = "Team Id should not be empty")
    var teamId: String,
    var roleDto: RoleDto
)