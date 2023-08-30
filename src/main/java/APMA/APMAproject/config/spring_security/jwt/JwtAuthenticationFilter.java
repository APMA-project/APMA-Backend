package APMA.APMAproject.config.spring_security.jwt;

import APMA.APMAproject.config.spring_security.auth.PrincipalDetails;
import APMA.APMAproject.config.spring_security.dto.LoginRequestDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("JwtAuthenticationFilter : 로그인 시도중");

        //1.username, password 받아서

        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {

            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
            System.out.println(loginRequestDto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword());

        System.out.println(authenticationToken);

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        //출력이 정상적으로 되면 로그인이 정상적으로 된것임
        PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("로그인 완료됨: " + principalDetailis.getUser().getPassword());
        System.out.println("Authentication : "+principalDetailis.getUser().getUsername());

        //authentication 객체가 session 영역에 저장됨
        //권한 관리를 security가 대신 해줌
        //권한 처리때문에 session 넣어줌

        return authentication;
    }

    //attempAthentication 실행 후 인증이 정상적으로 동작되면 successfulAuthentication 함수가 실행
    //JWT 토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("APMA toekn")
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.expirationTime))
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("username", principalDetailis.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.secret));

        response.addHeader(JwtProperties.headerString, jwtToken);
    }
}