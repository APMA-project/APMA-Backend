package APMA.APMAproject.config.spring_security.auth;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : 진입");

        if (username.contains("admin_")) {
            AdminEntity adminEntity = adminRepository.findByUsername(username);

            return new PrincipalDetails(adminEntity);
        } else {
            MemberEntity memberEntity = memberRepository.findByUsername(username);

            return new PrincipalDetails(memberEntity);
        }
    }
}