package hu.swarch.mpss.web

import hu.swarch.mpss.authentication.Role
import hu.swarch.mpss.authentication.UserService
import hu.swarch.mpss.dto.IdDTO
import hu.swarch.mpss.dto.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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
    fun putRole(@RequestBody data: UserDTO, model: Model): ResponseEntity<Unit> {
        userService.updateUser(data.username, Role.valueOf(data.role))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
    @DeleteMapping
    fun deleteUser(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        userService.deleteUser(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}