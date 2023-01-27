package kr.peerfund.role.repository

import kr.peerfund.role.dto.RoleType
import kr.peerfund.role.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleJpaRepository : JpaRepository<Role, Int> {
    fun findByRoleType(roleType: RoleType): Role?
}
