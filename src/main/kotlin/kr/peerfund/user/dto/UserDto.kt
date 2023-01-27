package kr.peerfund.user.dto

import kr.peerfund.user.model.User

data class UserDto(
    val id: Long,
    val username: String,
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                user.id,
                user.userName
            )
        }
    }
}
