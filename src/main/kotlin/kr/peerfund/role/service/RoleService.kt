package kr.peerfund.role.service

import kr.peerfund.role.dto.RoleType
import kr.peerfund.role.model.Role
import kr.peerfund.role.repository.RoleJpaRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleJpaRepository: RoleJpaRepository) {
    fun findByRoleType(roleType: RoleType): Role? {
        return roleJpaRepository.findByRoleType(roleType)
    }

    fun saveRole(roleType: RoleType): Role {
        return roleJpaRepository.save(
            Role(
                roleType = roleType,
                description = roleType.name.replace("ROLE_", "")
            )
        )
    }
}
