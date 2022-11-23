package hu.swarch.mpss.authentication

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "users")
data class User(
    @Id @GeneratedValue
    val id: Long = 0,
    @Column(unique = true)
    private val username: String,
    private val password: String,
    @Enumerated(EnumType.STRING)
    var role: Role
) : UserDetails {
    override fun getUsername() = username
    override fun getPassword() = password
    override fun getAuthorities() = role.authorities
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
