package APMA.APMAproject.dto.admin;

import APMA.APMAproject.constant.NoticeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class NoticeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeRequestDto {


        private String title;

        private String content;

        @NotNull(message = "Notice Type은 필수값입니다.")
        private NoticeType noticeType;



    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeResponseDto {

        private Long id;

        private String title;

        private String content;

        private NoticeType noticeType;

        private List<String> images;

        private Long adminId;





    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticePatchDto {


        @NotEmpty(message = "Title은 필수값입니다.")
        private String title;

        private String content;

         private List<String> images;

        private NoticeType noticeType;

        private String keyword;



    }

}
