package kr.peerfund.peerRroject.model

import kr.peerfund.peerProjectUser.model.PeerProjectUser
import kr.peerfund.peerRroject.dto.ProjectType
import kr.peerfund.peering.model.Peering
import kr.peerfund.user.model.User
import kr.peerfund.util.BaseEntity
import javax.persistence.*

@Entity
class PeerProject(
    @Column
    @Enumerated(EnumType.STRING)
    val projectType: ProjectType,

    @ManyToOne(fetch = FetchType.LAZY)
    var projectOwner: User?,

    @Column
    var title: String,

    @Column(columnDefinition = "TEXT")
    var introduce: String,

    @Column
    var recruitingCount: Int? = null,

    @Column
    val thumbnailImage: String? = null,

    @Column
    var introduceUrlLink: String? = null,

    @Column
    val tagString: String? = null,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    val peeringList: MutableList<Peering> = mutableListOf(),

    @Column
    var deposit: Int = 0,

    @OneToMany(mappedBy = "peerProject")
    val projectUserList: MutableList<PeerProjectUser> = mutableListOf(),

    @Id @GeneratedValue
    var id: Long = 0,
): BaseEntity() {

    fun addPeering(peering: Peering) {
        peering.project = this
        this.peeringList.add(peering)
    }

    fun toDto() {
     //TODO
    }
}