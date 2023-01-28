package kr.peerfund.peerRroject.service

import kr.peerfund.peerRroject.dto.RequestProjectDto
import kr.peerfund.peerRroject.repository.PeerProjectJpaRepository
import kr.peerfund.user.repository.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PeerProjectService(
    private val peerProjectJpaRepository: PeerProjectJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {

    @Transactional
    fun createPeerProject(requestProjectDto: RequestProjectDto) {
        val ownerName = requestProjectDto.projectOwnerName
        val owner = userJpaRepository.findByUserName(ownerName)
            ?: throw IllegalArgumentException("유저 정보 없음")

        val project = requestProjectDto.toEntity()
        owner.addOwnerProject(project)

        requestProjectDto.peeringList.map {
            project.addPeering(it.toEntity())
        }
    }
}