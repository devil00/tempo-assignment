package com.example.demo.adapters.controller

import com.example.demo.adapters.dto.UserDto
import com.example.demo.application.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/users")
@Validated
class MemberController(val memberService: MemberService) {

    @PostMapping("/{userId}/roles/{roleId}")
    fun addRole(@PathVariable @Valid @NotBlank userId: String, @Valid @PathVariable @Min(1) roleId: Int) : ResponseEntity<UserDto> {
        return ResponseEntity(memberService.addRole(userId, roleId), HttpStatus.CREATED)
    }

    @GetMapping
    fun sayHello() : ResponseEntity<String> {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }

    @PostMapping
    fun saveMember(userDto: UserDto) {
        memberService.saveMember(userDto)
    }

}