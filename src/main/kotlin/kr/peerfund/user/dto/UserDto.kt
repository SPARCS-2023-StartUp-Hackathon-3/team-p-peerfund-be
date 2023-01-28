package kr.peerfund.user.dto

import kr.peerfund.user.model.User

data class UserDto(
    val id: Long,
    val userName: String,
    val name: String,
    val roleType: String,
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                user.id,
                user.userName,
                user.name,
                user.roles.first().roleType.toString()
            )
        }
    }
}
