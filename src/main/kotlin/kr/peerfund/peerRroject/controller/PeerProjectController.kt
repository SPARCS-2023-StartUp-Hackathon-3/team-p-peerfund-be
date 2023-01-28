package kr.peerfund.peerRroject.controller

import io.swagger.annotations.Api
import kr.peerfund.dto.ResponseMessage
import kr.peerfund.peerRroject.dto.RequestProjectDto
import kr.peerfund.peerRroject.service.PeerProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api("PeerProject")
@RestController
@RequestMapping("/peerProject")
class PeerProjectController(
    private val peerProjectService: PeerProjectService
) {
    @PostMapping
    fun createPeerProject(
        @RequestBody(required = true) requestProjectDto: RequestProjectDto
    ): ResponseEntity<ResponseMessage> {
        return try {
            peerProjectService.createPeerProject(requestProjectDto)
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(e.message))
        }
    }
}