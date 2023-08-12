package APMA.APMAproject.domain.admin;


import APMA.APMAproject.constant.NoticeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "notice_images",
            joinColumns = @JoinColumn(name = "notice_id")
    )
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    public void updateNoticeImages(List<String> noticeImages) {
        this.images = noticeImages;
    }



}
