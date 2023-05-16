package com.example.demo.application.domain

import com.example.demo.application.domain.enums.RoleType
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(name = "role_type", unique = true)
    var roleType: String = RoleType.Dev.roleName,

    var isDefault: Boolean
)
