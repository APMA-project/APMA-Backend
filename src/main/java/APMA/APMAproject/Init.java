package APMA.APMAproject;

import APMA.APMAproject.constant.NoticeType;
import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.amin.NoticeRepository;
import APMA.APMAproject.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final NoticeRepository noticeRepository;

    @PostConstruct
    private void initFirst(){
        initAdmins();
        initMembers();
        initNotices();

    }

    @Transactional
    public void initAdmins() {
        for (int i = 1; i < 4; i++) {
            AdminEntity admin = new AdminEntity();
            admin.setUsername("admin_" + i);
            admin.setPassword(bCryptPasswordEncoder.encode("admin_" + i));
            adminRepository.save(admin);
        }

    }



    @Transactional
    public void initMembers() {
        for (int i = 1; i < 4; i++) {

            MemberEntity m = new MemberEntity();
            m.setUsername("member" + i);
            m.setPassword(bCryptPasswordEncoder.encode("member" + i));
            m.setEmail("APMA2023" + i + "@apma.com");
            m.setName("유재석" + i);
            m.setPhoneNumber("010-0000-0000_" + i);



            if (i == 0) {
                m.setBirthDay(LocalDate.of(2023, 1, 1));
            } else if (i == 1) {
                m.setBirthDay(LocalDate.of(2023, 2, 2));
            } else if (i == 2) {
                m.setBirthDay(LocalDate.of(2023, 3, 3));
            }

            memberRepository.save(m);
        }

    }

    @Transactional
    public void initNotices() {
        List<AdminEntity> a = adminRepository.findAll();
        for (int i = 1; i <= 10; i++) {
            NoticeEntity n = new NoticeEntity();
            n.setTitle("제목 " + i);
            n.setContent("Notice content " + i);
            n.setAdmin(a.get(1));
            n.setNoticeType(NoticeType.EVENT);
            noticeRepository.save(n);
        }
    }
}
