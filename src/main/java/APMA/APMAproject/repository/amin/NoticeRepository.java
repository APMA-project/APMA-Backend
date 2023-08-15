package APMA.APMAproject.repository.amin;

import APMA.APMAproject.constant.NoticeType;
import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.NoticeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    @Query("SELECT n FROM NoticeEntity n WHERE n.title LIKE %:keyword% OR n.content LIKE %:keyword%")
    Page<NoticeEntity> findKeyword(@Param("keyword") String keyword, Pageable pageable);


}