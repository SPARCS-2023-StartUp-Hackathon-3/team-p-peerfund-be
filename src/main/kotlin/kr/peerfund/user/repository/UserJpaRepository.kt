package kr.peerfund.user.repository

import kr.peerfund.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Int> {
    fun findByUserName(userName: String): User?
}
