package APMA.APMAproject.config.spring_security.jwt;

import APMA.APMAproject.config.spring_security.UserEntity;
import APMA.APMAproject.config.spring_security.auth.PrincipalDetails;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.member.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

//권한이나 인증이 필요한 특정 주소를 요청했을 때 BasicAuthenticationFilter 무조건 탐
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{


    private AdminRepository adminRepository;
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, AdminRepository adminRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("인가 필터 진입!!!");


        //JWT 토큰 검증해서 정상적인 사용자인지 확인
        String header = request.getHeader(JwtProperties.headerString);
        if (header == null || !header.startsWith(JwtProperties.tokenPrefix)) {
            log.info("토큰이 없음!! doFilter 수행");
            chain.doFilter(request, response);
            return;
        }
        System.out.println("header : " + header);
        String token = request.getHeader(JwtProperties.headerString)
                .replace(JwtProperties.tokenPrefix, "");

        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        // 내가 SecurityContext에 집적접근해서 세션을 만들때 자동으로 UserDetailsService에 있는 loadByUsername이 호출됨.
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.secret)).build().verify(token)
                .getClaim("username").asString();

        if (username != null) {
            UserEntity user;
            if (username.contains("admin_"))
                user = adminRepository.findByUsername(username);
            else user = memberRepository.findByUsername(username);

            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails, // 나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                    null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                    principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);

    }
}
