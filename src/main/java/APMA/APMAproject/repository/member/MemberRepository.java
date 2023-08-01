package APMA.APMAproject.repository.member;

import APMA.APMAproject.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


    @Query("SELECT m FROM MemberEntity m")
    List<MemberEntity> getAllMember();
}
