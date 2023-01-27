package kr.peerfund.dto

import kr.peerfund.role.dto.RoleType

data class AuthSignUpReqDto(
    val userName: String,
    val name: String,
    val password: String,
    val roleType: RoleType? = null
)
