package APMA.APMAproject.config.spring_security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(corsConfig.corsFilter())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/getAdmin")
                        .hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/user/**")
                        // ROLE_은 알아서 붙여줌!!
                        .hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/api/v1/manager/**")
                        .hasAnyRole("ADMIN")
                        .requestMatchers("/api/v1/admin/**")
                        .hasAnyRole("ADMIN")
                        .anyRequest().permitAll())
                .build();
    }


}
