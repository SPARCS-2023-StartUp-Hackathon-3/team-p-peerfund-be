package kr.peerfund.user.service

import kr.peerfund.dto.AuthSignInReqDto
import kr.peerfund.dto.AuthSignUpReqDto
import kr.peerfund.jwt.dto.JwtTokenResponseDto
import kr.peerfund.jwt.service.JwtTokenProvider
import kr.peerfund.role.dto.RoleType
import kr.peerfund.role.model.Role
import kr.peerfund.role.service.RoleService
import kr.peerfund.user.model.User
import kr.peerfund.user.repository.UserJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(
    private val authenticationManager: AuthenticationManager,
    private val tokenProvider: JwtTokenProvider,
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleService: RoleService
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    fun signIn(authSignInReqDto: AuthSignInReqDto): JwtTokenResponseDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authSignInReqDto.userName,
                authSignInReqDto.password
            )
        )
            // 인증이 완료되면, 인증 객체를 저장해줍니다.
            // 토큰을 만들어서 리턴하여 줍니다.
            .let { authentication ->
                logger.info("authentication: [$authentication]")
                SecurityContextHolder.getContext().authentication = authentication
                return JwtTokenResponseDto(tokenProvider.createToken(authentication))
            }
    }

    @Transactional
    fun signUp(authSignUpReqDto: AuthSignUpReqDto): User {
        val password = passwordEncoder.encode(authSignUpReqDto.password)
        val selectRole: Role = roleService.run {
            authSignUpReqDto.roleType?.let { roleType ->
                findByRoleType(roleType) ?: saveRole(roleType)
            } ?: findByRoleType(RoleType.ROLE_USER)!!
        }
        return userJpaRepository.save(
            User(
                userName = authSignUpReqDto.userName,
                password = password,
                name = authSignUpReqDto.name,
                roles = setOf(selectRole)
            )
        )
    }
}
