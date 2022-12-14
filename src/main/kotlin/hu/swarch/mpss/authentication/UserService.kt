package hu.swarch.mpss.authentication

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    fun createUser(username: String, password: String): User? {
        if (userRepository.existsByUsername(username.lowercase())) return null

        val user = User(
            username = username.lowercase(),
            password = passwordEncoder.encode(password),
            role = Role.BASIC_USER
        )

        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username.lowercase()) ?: throw UsernameNotFoundException(username)
    }

    fun updateUser(id: Long, role: Role): User? {
        val user = userRepository.findByIdOrNull(id) ?: return null
        user.role = role
        return userRepository.save(user)
    }

    fun findAllUsers() : List<User> {
        return userRepository.findAll()
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
