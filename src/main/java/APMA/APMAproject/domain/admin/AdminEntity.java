package APMA.APMAproject.domain.admin;

import APMA.APMAproject.config.spring_security.UserEntity;
import APMA.APMAproject.constant.Role;
import APMA.APMAproject.domain.member.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity implements UserEntity {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;


    @Enumerated(EnumType.STRING)
    private final Role role = Role.ADMIN;

    private String password;



}
