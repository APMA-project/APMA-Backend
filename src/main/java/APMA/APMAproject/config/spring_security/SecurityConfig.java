package APMA.APMAproject.config.spring_security;


import APMA.APMAproject.config.spring_security.jwt.JwtAuthenticationFilter;
import APMA.APMAproject.config.spring_security.jwt.JwtAuthorizationFilter;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminRepository adminRepository;


//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public AuthenticationManager authenticationManager(
    ) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(corsConfig.corsFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository, adminRepository))
                .authorizeHttpRequests(authorize -> authorize

                        //수정 좀 부탁드려요...

//                        .requestMatchers("/APMA/admin/**")
//                        .hasAnyRole("ADMIN")
//                        .requestMatchers("/APMA/member/**")
//                        // ROLE_은 알아서 붙여줌!!
//                        .hasAnyRole("ADMIN", "MEMBER")
//                        .anyRequest().permitAll())
//                .build();


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
