package kr.peerfund.peering.repository

import kr.peerfund.peering.model.Peering
import org.springframework.data.jpa.repository.JpaRepository

interface PeeringJpaRepository : JpaRepository<Peering, Long> {
}