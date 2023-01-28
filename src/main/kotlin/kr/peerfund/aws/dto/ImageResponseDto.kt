package kr.peerfund.aws.dto

data class SuccessImageResponseDto(
    val response: String,
    val url: String,
    val name: String,
)

data class FailImageResponseDto(
    val err: String,
    val response: String,
    val file: String,
)
