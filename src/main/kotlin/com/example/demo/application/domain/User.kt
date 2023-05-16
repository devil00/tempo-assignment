package com.example.demo.application.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    var id : String = "",
    var firstName: String = "",
    var lastName: String = "",
    var displayName: String = "",
    var avatarUrl: String = "",
    var location: String = "",

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [
            JoinColumn(name = "user_id", referencedColumnName = "id")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "role_id", referencedColumnName = "id")
        ]
    )
    var roles: List<Role> = emptyList()
){

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', displayName=${displayName}, avatarUrl=${avatarUrl}, location=${location}, roles=$roles)"
//        return "User(id=$id, roles=$roles)"
    }
}
