package com.example.demo.adapters.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class RoleDto (
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var id: Int?,

    @field:NotBlank(message = "Role name should not be blank")
    var roleType: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var isDefault: Boolean
)