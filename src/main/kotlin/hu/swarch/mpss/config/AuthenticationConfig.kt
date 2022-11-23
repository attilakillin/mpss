package hu.swarch.mpss.config

import hu.swarch.mpss.authentication.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class MPSSEncoderConfig {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}

@Configuration
class MPSSUserDetailsServiceConfig(private val userService: UserService) {
    @Bean
    fun userDetailsService(): UserDetailsService = userService
}
