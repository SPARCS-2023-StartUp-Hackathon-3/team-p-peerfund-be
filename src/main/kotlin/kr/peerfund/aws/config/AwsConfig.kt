package kr.peerfund.aws.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {
    @Value("\${spring.aws.access-key}")
    val accessKey: String = ""
    @Value("\${spring.aws.secret-key}")
    val secretKey: String = ""
    @Value("\${spring.aws.region}")
    val region: String = ""
    @Value("\${spring.aws.bucket}")
    val buket: String = ""
    @Value("\${spring.aws.url}")
    val url: String = ""

    @Bean
    fun amazonS3Client(awsConfig: AwsConfig): AmazonS3Client {
        val credentials =
            AWSStaticCredentialsProvider(BasicAWSCredentials(awsConfig.accessKey, awsConfig.secretKey))
        return AmazonS3ClientBuilder.standard()
            .withCredentials(credentials)
            .withRegion(Regions.fromName(awsConfig.region))
            .build() as AmazonS3Client
    }
}