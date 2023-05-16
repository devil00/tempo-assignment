package com.example.demo.application.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MemberShipKey(
    @Column(name = "user_id")
    var userId: String,

    @Column(name = "team_id")
    var teamId: String
) : Serializable {
    override fun toString(): String = "MemberShipKey($userId, $teamId)"
}
