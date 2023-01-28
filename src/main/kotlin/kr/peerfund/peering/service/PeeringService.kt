package kr.peerfund.peering.service

import kr.peerfund.peerRroject.repository.PeerProjectJpaRepository
import kr.peerfund.peering.dto.RequestPeeringDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PeeringService(
    private val projectJpaRepository: PeerProjectJpaRepository,
) {

    @Transactional
    fun addPeerToProject(requestProjectDto: RequestPeeringDto) {
        val project = projectJpaRepository.findById(requestProjectDto.projectId)
            .orElseThrow { IllegalArgumentException("프로젝트가 없습니다.") }
        requestProjectDto.peeringList.map {
            project.addPeering(it.toEntity())
        }
    }
}