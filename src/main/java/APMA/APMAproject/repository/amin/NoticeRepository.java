package APMA.APMAproject.repository.amin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

//    @Query("SELECT n FROM NoticeEntity n WHERE n.title LIKE %:keyword% OR n.content LIKE %:keyword%")
//    Page<NoticeEntity> findKeyword(String keyword, Pageable pageable);

    @Query("SELECT n FROM NoticeEntity n WHERE n.title LIKE %:keyword% OR n.content LIKE %:keyword%")
    List<NoticeEntity> findKeyword(String keyword);

}