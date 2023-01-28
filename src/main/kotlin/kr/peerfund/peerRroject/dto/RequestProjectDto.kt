package kr.peerfund.peerRroject.dto

import kr.peerfund.peerRroject.model.PeerProject
import kr.peerfund.peering.dto.RequestPeerDto

data class RequestProjectDto(
    val projectOwnerName: String,
    val projectType: ProjectType,
    val title: String,
    val introduce: String,
    val thumbnailImageUrl: String,
    val introduceUrlLink: String,
    val tagString: String,
    val peeringList: MutableList<RequestPeerDto>,
    val deposit: Int,
) {
    fun toEntity(): PeerProject {
        return PeerProject(
            projectType = this.projectType,
            projectOwner = null,
            title = this.title,
            introduce = this.introduce,
            thumbnailImage = thumbnailImageUrl,
            introduceUrlLink = this.introduceUrlLink,
            tagString = this.tagString,
            deposit = this.deposit,
        )
    }
}
