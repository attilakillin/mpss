package hu.swarch.mpss.config

import hu.swarch.mpss.authentication.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/basic_parts", "/basic_parts/**").permitAll()
                .antMatchers("/production_goals", "/production_goals/**").permitAll()
                .antMatchers("/complex_parts", "/complex_parts/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService = userService

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        builder.userDetailsService(userService).passwordEncoder(passwordEncoder)

        return builder.build()
    }
}
