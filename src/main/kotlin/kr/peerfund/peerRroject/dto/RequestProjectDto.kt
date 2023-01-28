package kr.peerfund.peerRroject.dto

import kr.peerfund.peerRroject.model.PeerProject
import kr.peerfund.user.model.User

data class RequestProjectDto(
    val projectOwnerName: String,
    val projectType: ProjectType,
    val title: String,
    val introduce: String,
    val thumbnailImageUrl: String,
    val introduceUrlLink: String,
    val tagString: String,
) {
    fun toEntity(owner: User): PeerProject {
        return PeerProject(
            projectType = this.projectType,
            projectOwner = owner,
            title = this.title,
            introduce = this.introduce,
            thumbnailImage = thumbnailImageUrl,
            introduceUrlLink = this.introduceUrlLink,
            tagString = this.tagString,
        )
    }
}
