package APMA.APMAproject.domain.admin;

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
public class AdminEntity {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;




}
