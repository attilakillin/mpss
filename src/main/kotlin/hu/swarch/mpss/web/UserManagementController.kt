package hu.swarch.mpss.web

import hu.swarch.mpss.authentication.Role
import hu.swarch.mpss.authentication.UserService
import hu.swarch.mpss.dto.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user_management")
class UserManagementController (
    val userService: UserService
) {
    @GetMapping
    fun getUserManagementPage(model: Model) : String {
        model.addAttribute("users", userService.getUsers())
        model.addAttribute("roles", Role.values())
        return "user_management"
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: UserDTO, model: Model): ResponseEntity<Unit> {
        userService.updateUser(data.username, Role.valueOf(data.role))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}