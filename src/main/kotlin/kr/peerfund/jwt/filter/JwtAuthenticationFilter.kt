package kr.peerfund.jwt.filter

import kr.peerfund.jwt.service.JwtTokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        // 1. 요청으로부터 토큰을 가져오고, 유효성 검사를 해줍니다.
        // 2. 토큰의 유효성 검사가 완료되면 Security의 Authentication에 토큰을 저장해줍니다.
        jwtTokenProvider.resolveToken((request as HttpServletRequest)).let {
            if (it != null && jwtTokenProvider.validateToken(it)) {
                SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(it)
                logger.info("[IS VALID TOKEN]")
            }
        }
        chain!!.doFilter(request, response)
    }
}
