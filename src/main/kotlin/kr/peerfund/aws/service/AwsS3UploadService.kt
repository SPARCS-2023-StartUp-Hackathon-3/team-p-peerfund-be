package kr.peerfund.aws.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.StorageClass
import com.amazonaws.util.IOUtils
import kr.peerfund.aws.config.AwsConfig
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.time.OffsetDateTime.now
import java.time.ZoneId

@Service
class AwsS3UploadService(
    private val amazonS3: AmazonS3Client,
    private val awsConfig: AwsConfig,
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)
    fun uploadImage(bucket: String? = awsConfig.buket, multipartFile: MultipartFile): String {
        val date = now(ZoneId.of("Asia/Seoul")).toLocalDate()
        val fileName =
            "image/${date.year}/${date.month}-${date.dayOfMonth}/${System.currentTimeMillis()}-${multipartFile.originalFilename}"
        val inputStream = multipartFile.inputStream
        val metadata = ObjectMetadata()
        val postfix = when (val extension = fileName.substringAfter(".")) {
            "jpg" -> "image/jpeg"
            "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "" -> "image/jpeg"
            else -> throw IllegalArgumentException("${extension}은 이미지 확장자가 아닙니다.")
        }
        val bytes = IOUtils.toByteArray(inputStream)
        metadata.contentType = "application/$postfix"
        metadata.contentLength = bytes.size.toLong()
        putObject(bucket!!, fileName, ByteArrayInputStream(bytes), metadata)

        return "https://${awsConfig.buket}.s3.${awsConfig.region}.amazonaws.com/$fileName"
    }

    fun putObject(bucket: String, fileName: String, inputStream: InputStream, metadata: ObjectMetadata) {
        logger.info("UPLOAD IMAGE : $fileName")
        val putObjectRequest = PutObjectRequest(bucket, fileName, inputStream, metadata)
            .withStorageClass(StorageClass.IntelligentTiering)
        amazonS3.putObject(putObjectRequest)
    }

    fun getObjectOrNull(bucket: String, fileName: String): S3Object? {
        return amazonS3.getObject(bucket, fileName)
    }
}






