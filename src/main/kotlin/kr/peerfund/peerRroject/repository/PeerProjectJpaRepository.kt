package kr.peerfund.peerRroject.repository

import kr.peerfund.peerRroject.model.PeerProject
import org.springframework.data.jpa.repository.JpaRepository

interface PeerProjectJpaRepository : JpaRepository<PeerProject, Long>