package kr.peerfund.peering.dto

data class RequestPeeringDto(
    val projectId: Long,
    val peeringList: MutableList<RequestPeerDto>
)
