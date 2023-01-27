package kr.peerfund.jwt.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import kr.peerfund.add
import kr.peerfund.configure.SecurityProperties
import kr.peerfund.jwt.dto.UserDetailsPrincipal
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val securityProperties: SecurityProperties,
    private val userDetailService: AppUserDetailsService,
) {
    // 토큰 만들기
    fun createToken(authentication: Authentication?): String = Jwts.builder().let {
        val now = Date()
        val userDetailsPrincipal = authentication?.principal as UserDetailsPrincipal
        it.setClaims(
            Jwts.claims().setSubject(userDetailsPrincipal.username)
                .also { claims ->
                    claims[securityProperties.authorityKey] = userDetailsPrincipal.authorities.map { it.authority }
                }
        )
            .setIssuedAt(now)
            .setExpiration(now.add(Calendar.MINUTE, securityProperties.expirationTime))
            .signWith(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()), SignatureAlgorithm.HS256)
            .compact()
    }!!

    // Authentication 객체 가져오기
    fun getAuthentication(token: String): Authentication {
        return userDetailService.loadUserByUsername(getUserId(token)).let {
            UsernamePasswordAuthenticationToken(it, it.password, it.authorities)
        }
    }

    // 사용자 Id 가져오기
    fun getUserId(token: String): String = availableToken(token)?.body?.subject!!

    // 요청으로부터 토큰 가져오기
    fun resolveToken(req: HttpServletRequest) = req.getHeader(securityProperties.headerString)?.run {
        securityProperties.tokenPrefix.let {
            if (startsWith(it)) substring(securityProperties.tokenPrefix.length, length)
            else null
        }
    }

    fun availableToken(jwtToken: String): Jws<Claims>? = try {
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()))
            .build()
            .parseClaimsJws(jwtToken).let {
                if (!it.body.expiration.before(Date())) {
                    it
                } else null
            }
    } catch (e: Exception) {
        null
    }

    // 토큰 유효성 검사
    fun validateToken(jwtToken: String): Boolean = availableToken(jwtToken) != null
}
