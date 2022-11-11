package hu.swarch.mpss.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/basic_parts", "/basic_parts/**").permitAll()
                .antMatchers("/production_goals", "/production_goals/**").permitAll()
                .anyRequest().authenticated()

        return http.build()
    }
}
