package APMA.APMAproject.repository.amin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    @Query("SELECT n FROM NoticeEntity n")
    List<NoticeEntity> getAllNotice(); //todo : 나중에 페이징 처리도 필요할듯. 이건 나중에 합시다

}
