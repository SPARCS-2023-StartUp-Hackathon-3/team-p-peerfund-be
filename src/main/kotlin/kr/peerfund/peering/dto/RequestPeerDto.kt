package kr.peerfund.peering.dto

import kr.peerfund.peerRroject.dto.Subject
import kr.peerfund.peering.model.Peering

data class RequestPeerDto(
    val subject: Subject,
    val needCount: Int,
    val requiredKnowledge: String,
) {
    fun toEntity(): Peering {
        return Peering(
            null,
            this.subject.subjectName,
            this.requiredKnowledge,
            this.needCount,
        )
    }
}
