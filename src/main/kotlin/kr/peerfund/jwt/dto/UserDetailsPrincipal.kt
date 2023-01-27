package kr.peerfund.jwt.dto

import kr.peerfund.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsPrincipal(
    private val user: User
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        user.roles.forEach { authorities.add { SimpleGrantedAuthority(it.roleType.name).authority } }
        return authorities
    }

    override fun getPassword() = user.password
    override fun getUsername() = user.userName
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
