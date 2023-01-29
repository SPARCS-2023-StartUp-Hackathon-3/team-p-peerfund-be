package kr.peerfund.mypage.model

import kr.peerfund.user.model.User
import kr.peerfund.util.BaseEntity
import javax.persistence.*

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

    @OneToOne(mappedBy = "myPage")
    var user: User?,

    @Id @GeneratedValue var id: Long = 0L
):BaseEntity() {
}