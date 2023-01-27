package kr.peerfund.jwt.service

import kr.peerfund.jwt.dto.UserDetailsPrincipal
import kr.peerfund.user.repository.UserJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    private val userJpaRepository: UserJpaRepository
) : UserDetailsService {

    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        logger.info("[유저 조회]")
        val user = userJpaRepository.findByUserName(userName)
            ?: throw UsernameNotFoundException("${userName}의 유저가 존재하지 않습니다.")

        return UserDetailsPrincipal(user)
    }
}
