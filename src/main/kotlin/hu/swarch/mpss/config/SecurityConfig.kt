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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

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
                .antMatchers("/basic_parts", "/basic_parts/**").hasAuthority("MANAGE_BASIC_PARTS")
                .antMatchers("/complex_parts", "/complex_parts/**").hasAuthority("MANAGE_COMPLEX_PARTS")
                .antMatchers("/production_goals", "/production_goals/**").hasAuthority("MANAGE_PROD_GOALS")
                .antMatchers("/user_management", "/user_management/**").hasAuthority("MANAGE_USERS")
                .antMatchers("/procurement_overview", "/procurement_overview/**").hasAuthority("SEE_PROCUREMENT_OVERVIEW")
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/auth/forbidden")
                .and()
            .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/auth/logout", "GET"))
                .logoutSuccessUrl("/")

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
