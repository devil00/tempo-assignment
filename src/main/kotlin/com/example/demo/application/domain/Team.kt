package com.example.demo.application.domain

//@Entity
//@Table(name = "teams")
data class Team (
//    @Id
    var id: String,
    var name: String,
    var teamLeadId: String,
    var teamMemberIds: Set<String>
)
