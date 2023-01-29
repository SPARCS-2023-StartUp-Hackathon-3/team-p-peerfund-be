package kr.peerfund.peerprojectUser.repository

import kr.peerfund.peerprojectUser.model.PeerProjectUser
import org.springframework.data.jpa.repository.JpaRepository

interface PeerProjectUserJpaRepository : JpaRepository<PeerProjectUser, Long> {
}