package kr.peerfund.peering.model

import kr.peerfund.peerRroject.model.PeerProject
import kr.peerfund.util.BaseEntity
import javax.persistence.*

@Entity
class Peering(
    @ManyToOne(fetch = FetchType.LAZY)
    var project: PeerProject?,

    @Column
    val subject: String,

    @Column(columnDefinition = "TEXT")
    var requiredKnowledge: String,

    @Column
    var needCount: Int = 0,

    @Id @GeneratedValue val id: Long = 0L,
): BaseEntity()