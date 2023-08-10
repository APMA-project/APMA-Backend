package APMA.APMAproject.domain.member;

import APMA.APMAproject.config.spring_security.UserEntity;
import APMA.APMAproject.constant.Role;
import APMA.APMAproject.domain.admin.AdminEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity implements UserEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final Role role = Role.MEMBER;

    private String username; //멤버 아이디


    private String name;

    private String email;

    private String password;

    private String phoneNumber;


    @Column(columnDefinition = "DATE") // 컬럼 정의에 DATE 타입을 사용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDay;


}
