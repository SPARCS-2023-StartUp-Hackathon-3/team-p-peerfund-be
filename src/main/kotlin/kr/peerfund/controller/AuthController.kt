package kr.peerfund.controller

import kr.peerfund.dto.AuthSignInReqDto
import kr.peerfund.dto.AuthSignUpReqDto
import kr.peerfund.jwt.dto.JwtTokenResponseDto
import kr.peerfund.user.model.User
import kr.peerfund.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @PostMapping("/sign-in")
    fun signIn(@RequestBody authSignInReqDto: AuthSignInReqDto): ResponseEntity<JwtTokenResponseDto> {
        logger.info("[SIGN IN]")
        val token = userService.signIn(authSignInReqDto)
        logger.info("username:  ${authSignInReqDto.userName}, token: $token")
        return ResponseEntity.ok(token)
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody authSignUpReqDto: AuthSignUpReqDto): ResponseEntity<User> {
        logger.info("[SIGN UP]")
        logger.info("username:  ${authSignUpReqDto.userName}")
        return ResponseEntity.ok(userService.signUp(authSignUpReqDto))
    }
}
