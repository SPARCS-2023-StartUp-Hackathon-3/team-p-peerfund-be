package kr.peerfund.mypage.dto

import kr.peerfund.mypage.model.MyPage

data class RequestMyPageDto(
    val name: String,
    val subject: String?,
    val university: String?,
    val portfolioLink: String?,
    val introduce: String?,
    val skills: String?,
    val experience: String?,
) {
    fun toEntity(): MyPage {
        return MyPage(
            this.name,
            this.subject,
            this.university,
            this.portfolioLink,
            this.introduce,
            this.skills,
            this.experience,
        )
    }
}