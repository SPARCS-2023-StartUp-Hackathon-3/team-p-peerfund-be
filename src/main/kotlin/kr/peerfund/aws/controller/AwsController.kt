package kr.peerfund.aws.controller

import kr.peerfund.aws.service.AwsS3UploadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class AwsController(
    private val awsS3UploadService: AwsS3UploadService
) {

    @PostMapping("/aws/image")
    fun updateImage(@RequestParam("image") multipartFile: MultipartFile): ResponseEntity<HttpStatus> {
        try {
            awsS3UploadService.uploadImage(multipartFile = multipartFile)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
