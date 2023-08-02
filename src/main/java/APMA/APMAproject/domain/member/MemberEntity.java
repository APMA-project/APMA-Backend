package APMA.APMAproject.domain.member;

import APMA.APMAproject.domain.admin.AdminEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId; //멤버 아이디

    private String name;

    private String email;

    private String password;

    private String phoneNumber;


}
