package kr.peerfund.user.controller

import javassist.NotFoundException
import kr.peerfund.jwt.dto.UserDetailsPrincipal
import kr.peerfund.user.dto.UserDto
import kr.peerfund.user.repository.UserJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userJpaRepository: UserJpaRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @GetMapping
    fun getUser(): UserDto {
        logger.info("GET USER")
        val user = SecurityContextHolder.getContext().authentication.principal
            .let { principal ->
                logger.info("principal: $principal.toString()")
                when (principal) {
                    // 객체 타입이 UserPrincial인면 username을 받아옵니다.
                    is UserDetailsPrincipal -> principal.username
                    else -> throw InternalAuthenticationServiceException("Can not found matched User Principal")
                }.let { id -> userJpaRepository.findByUserName(id) }
            } ?: throw NotFoundException("멤버가 없음")
        return UserDto.from(user)
    }
}
