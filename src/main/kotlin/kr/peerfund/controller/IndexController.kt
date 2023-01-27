package kr.peerfund.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class IndexController {
    @Value("\${spring.profiles.active:Unknown}")
    lateinit var activeProfile: String

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("profile", "Profile: [$activeProfile]")
        return "index"
    }
}
