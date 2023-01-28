package kr.peerfund.peerRroject.service

import kr.peerfund.peerRroject.dto.RequestProjectDto
import kr.peerfund.peerRroject.repository.PeerProjectJpaRepository
import kr.peerfund.user.repository.UserJpaRepository
import org.springframework.stereotype.Service

@Service
class PeerProjectService(
    private val peerProjectJpaRepository: PeerProjectJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {

    fun createPeerProject(requestProjectDto: RequestProjectDto) {
        val ownerName = requestProjectDto.projectOwnerName
        val user = userJpaRepository.findByUserName(ownerName)
            ?: throw IllegalArgumentException("유저 정보 없음")

        peerProjectJpaRepository.save(requestProjectDto.toEntity(user))
    }
}