package com.example.demo.adapters.persistence

import com.example.demo.application.domain.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : CrudRepository<Role, Int>