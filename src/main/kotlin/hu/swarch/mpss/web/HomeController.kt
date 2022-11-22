package hu.swarch.mpss.web

import hu.swarch.mpss.authentication.Role
import hu.swarch.mpss.authentication.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {

    @GetMapping
    fun getHomeAndRedirect(@AuthenticationPrincipal user: User): String {
        return when (user.role) {
            Role.PROCUREMENT -> "redirect:/basic_parts"
            Role.PRODUCTION_MANAGER -> "redirect:/complex_parts"
            Role.MANAGER -> "redirect:/production_goals"
            Role.ADMINISTRATOR -> "redirect:/user_management"
            else -> "home"
        }
    }
}
