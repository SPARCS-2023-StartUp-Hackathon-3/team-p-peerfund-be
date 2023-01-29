package kr.peerfund.mypage.service

import com.amazonaws.services.connect.model.UserNotFoundException
import kr.peerfund.mypage.dto.RequestMyPageDto
import kr.peerfund.mypage.repository.MyPageJpa
import kr.peerfund.user.repository.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MyPageService(
    private val myPageJpa: MyPageJpa,
    private val userJpaRepository: UserJpaRepository,
) {
    @Transactional
    fun createMyPage(requestMyPageDto: RequestMyPageDto) {
        val user = (userJpaRepository.findByUserName(requestMyPageDto.name)
            ?: throw UserNotFoundException("유저정보가 없습니다."))
        user.createMyPage(requestMyPageDto.toEntity())
    }
}