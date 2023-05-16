package com.example.demo.application.domain

import javax.persistence.*

@Entity
@Table(name = "user_team_relation")
data class MemberShip(
    @EmbeddedId
    var id: MemberShipKey,

    @OneToOne(cascade= [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = true)
    var role: Role

)
