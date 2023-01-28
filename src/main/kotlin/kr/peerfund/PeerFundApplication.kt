package kr.peerfund

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class PeerFundApplication

fun main(args: Array<String>) {
    runApplication<PeerFundApplication>(*args)
}
