package com.example.demo.adapters.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class UpdateRoleRequest {
    @field:NotNull
    @field:Min(1)
    var roleId: Int = -1
}
