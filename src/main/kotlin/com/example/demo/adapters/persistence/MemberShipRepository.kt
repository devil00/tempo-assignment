package com.example.demo.adapters.persistence

import com.example.demo.application.domain.MemberShip
import com.example.demo.application.domain.MemberShipKey
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberShipRepository : CrudRepository<MemberShip, MemberShipKey> {
    @Query("SELECT m FROM MemberShip m WHERE m.role.id = ?1")
    fun findMemberShipByRole(roleId: Int): MemberShip?
}