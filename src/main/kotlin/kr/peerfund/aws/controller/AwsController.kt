package kr.peerfund.aws.controller

import kr.peerfund.aws.dto.FailImageResponseDto
import kr.peerfund.aws.dto.SuccessImageResponseDto
import kr.peerfund.aws.service.AwsS3UploadService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/aws")
class AwsController(
    private val awsS3UploadService: AwsS3UploadService
) {

    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @PostMapping("/image")
    fun uploadImage(@RequestParam image: MultipartFile): ResponseEntity<Any> {
        return try {
            val uploadImageUrl = awsS3UploadService.uploadImage(multipartFile = image)
            ResponseEntity.status(HttpStatus.OK).body(
            SuccessImageResponseDto(
                response = HttpStatus.OK.name,
                url = uploadImageUrl,
                name = image.originalFilename.toString()
            ))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.OK).body(
                FailImageResponseDto(
                err = e.message.toString(),
                response = HttpStatus.BAD_REQUEST.name,
                file = image.originalFilename.toString()
            ))
        }
    }
}
