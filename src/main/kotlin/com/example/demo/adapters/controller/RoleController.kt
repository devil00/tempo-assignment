package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.MemberShipDto
import com.example.demo.adapters.dto.RoleDto
import com.example.demo.application.service.MemberShipService
import com.example.demo.application.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/roles")
@Validated
class RoleController(val roleService: RoleService, val memberShipService: MemberShipService) {
    @PostMapping
    fun createRole(@Valid @RequestBody role : RoleDto) : ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.createRole(role), HttpStatus.CREATED)
    }

    @GetMapping("/{roleId}")
    fun getRole(@PathVariable @Valid @Min(1) roleId: Int): ResponseEntity<RoleDto> {
        return ResponseEntity(roleService.getRoleById(roleId), HttpStatus.OK)
    }

    @GetMapping("/{roleId}/membership")
    fun findMembership(@PathVariable @Valid @Min(1) roleId: Int): ResponseEntity<MemberShipDto> {
        return ResponseEntity(memberShipService.findMemberShipByRole(roleId), HttpStatus.OK)
    }
}