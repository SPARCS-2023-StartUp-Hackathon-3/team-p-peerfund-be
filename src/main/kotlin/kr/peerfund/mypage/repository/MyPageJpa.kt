package kr.peerfund.mypage.repository

import kr.peerfund.mypage.model.MyPage
import org.springframework.data.jpa.repository.JpaRepository

interface MyPageJpa : JpaRepository<MyPage, Long> {
}