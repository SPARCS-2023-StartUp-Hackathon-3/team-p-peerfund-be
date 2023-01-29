package kr.peerfund.mypage.controller

import kr.peerfund.dto.ResponseMessage
import kr.peerfund.dto.Status.SUCCESS
import kr.peerfund.mypage.dto.RequestMyPageDto
import kr.peerfund.mypage.service.MyPageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("myPage")
class MyPageController(
    private val myPageService: MyPageService
) {

    @PostMapping
    fun createMyPage(
        @RequestBody requestMyPageDto: RequestMyPageDto
    ): ResponseEntity<ResponseMessage> {
        return try {
            myPageService.createMyPage(requestMyPageDto)
            ResponseEntity.status(HttpStatus.OK).body(ResponseMessage(SUCCESS.name))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(e.message))
        }
    }
}