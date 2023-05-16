package com.example.demo.adapters.persistence

import com.example.demo.application.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<User, String>