package kr.peerfund.peerProjectUser.repository

import kr.peerfund.peerProjectUser.model.PeerProjectUser
import org.springframework.data.jpa.repository.JpaRepository

interface PeerProjectUserJpaRepository : JpaRepository<PeerProjectUser, Long> {
}