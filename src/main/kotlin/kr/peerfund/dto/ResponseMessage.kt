package kr.peerfund.dto

data class ResponseMessage(
    val message: String?
)

enum class Status {
    SUCCESS, FAIL
}