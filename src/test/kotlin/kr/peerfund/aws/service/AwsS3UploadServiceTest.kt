package kr.peerfund.aws.service

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kr.peerfund.aws.config.AwsConfig
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.TestConstructor
import java.io.File
import java.io.FileInputStream


@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(classes = [AwsConfig::class, AwsS3UploadService::class])
class AwsS3UploadServiceTest(
    private val awsS3UploadService: AwsS3UploadService
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)
    @Test
    fun `s3 업로드 후 경로 URL을 반환받는다`() {
        // given
        val multipartFile = MockMultipartFile("spring.jpeg", FileInputStream(File("/Users/groom.siki/Git/peerfund/src/main/resources/spring.jpeg")))

        // when
        val uploadPath = awsS3UploadService.uploadImage(multipartFile = multipartFile)

        // then
        logger.info("[$uploadPath]")
        uploadPath shouldNotBe null
        uploadPath shouldNotBe ""
        uploadPath.contains("spring") shouldBe true
    }
}