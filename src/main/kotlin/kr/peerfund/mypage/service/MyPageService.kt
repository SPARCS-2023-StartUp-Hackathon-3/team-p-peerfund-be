package kr.peerfund.mypage.service

import kr.peerfund.mypage.dto.RequestMyPageDto
import kr.peerfund.mypage.repository.MyPageJpa
import org.springframework.stereotype.Service

@Service
class MyPageService(
    private val myPageJpa: MyPageJpa
) {
    fun createMyPage(requestMyPageDto: RequestMyPageDto) {
        myPageJpa.save(requestMyPageDto.toEntity())
    }
}