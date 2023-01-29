package kr.peerfund.mypage.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MyPage(
    @Column
    val name: String,

    @Column
    val subject: String?,

    @Column
    val university: String?,

    @Column
    val portfolioLink: String?,

    @Column
    val introduce: String?,

    @Column
    val skills: String?,

    @Column
    val experience: String?,

    @Id @GeneratedValue var id: Long = 0L
) {
}