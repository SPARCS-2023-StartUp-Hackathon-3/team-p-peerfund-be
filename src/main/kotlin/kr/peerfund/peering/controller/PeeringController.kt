package kr.peerfund.peering.controller

import kr.peerfund.dto.ResponseMessage
import kr.peerfund.dto.Status.*
import kr.peerfund.peering.dto.RequestPeeringDto
import kr.peerfund.peering.service.PeeringService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/peering")
class PeeringController(
    private val peeringService: PeeringService
) {
    @PostMapping
    fun addPeering(
        @RequestBody requestPeeringDto: RequestPeeringDto
    ): ResponseEntity<ResponseMessage> {
        return try {
            peeringService.addPeerToProject(requestPeeringDto)
            ResponseEntity.status(HttpStatus.OK).body(ResponseMessage(SUCCESS.name))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(e.message))
        }
    }
}