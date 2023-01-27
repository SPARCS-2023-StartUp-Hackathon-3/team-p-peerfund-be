package kr.peerfund.role.service

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kr.peerfund.role.dto.RoleType
import kr.peerfund.role.model.Role
import kr.peerfund.role.repository.RoleJpaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RoleServiceTest {
    private val roleJpaRepository: RoleJpaRepository = mockk()

    private val roleService = RoleService(roleJpaRepository)

    @Test
    fun `RoleType으로 권한을 조회한다`() {
        // given
        val role = Role(RoleType.ROLE_ADMIN)
        every { roleJpaRepository.findByRoleType(RoleType.ROLE_USER) } returns role

        // when
        val findByRoleType = roleService.findByRoleType(RoleType.ROLE_USER)

        // then
        findByRoleType!!.roleType.name shouldBe role.roleType.name
    }
}