package hu.swarch.mpss.authentication

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/auth")
class AuthenticationController(
    private val userService: UserService
) {

    @GetMapping("/register")
    fun getRegistrationPage(): String {
        return "user_register"
    }

    @PostMapping("/register")
    fun postRegistration(@RequestParam username: String, @RequestParam password: String, model: Model): String {
        if (userService.createUser(username, password) == null) {
            model.addAllAttributes(mapOf("error" to true, "username" to username, "password" to password))
        } else {
            model.addAttribute("success", true)
        }
        return "user_register"
    }

    @GetMapping("/login")
    fun getLoginPage(): String {
        return "user_login"
    }

    @GetMapping("/forbidden")
    fun getForbiddenErrorPage(): String {
        return "error_forbidden"
    }
}
