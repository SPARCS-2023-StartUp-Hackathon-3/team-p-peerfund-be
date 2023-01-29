package kr.peerfund.peerprojectUser.model

import kr.peerfund.peerRroject.model.PeerProject
import kr.peerfund.user.model.User
import kr.peerfund.util.BaseEntity
import javax.persistence.*

@Entity
class PeerProjectUser(
    @ManyToOne(fetch = FetchType.LAZY)
    val peerProject: PeerProject,

    @ManyToOne(fetch = FetchType.LAZY)
    val projectUser: User,

    @Id @GeneratedValue val id: Long = 0L,
): BaseEntity() {
}