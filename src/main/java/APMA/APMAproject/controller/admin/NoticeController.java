package APMA.APMAproject.controller.admin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.dto.admin.NoticeDto;
import APMA.APMAproject.service.admin.NotcieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("APMA/admin")
public class NoticeController {

    private final NotcieService noticeService;

    @PostMapping("/createNotice")
    public ResponseEntity<NoticeDto.NoticeResponseDto> createNotice(@RequestParam("adminId") Long adminId, @RequestBody NoticeDto.NoticeRequestDto noticeRequestDto) {
        NoticeDto.NoticeResponseDto responseDto = noticeService.createNotice(adminId, noticeRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/getNotice")
    public ResponseEntity<?> getNotice (@RequestParam("noticeId") Long noticeId) {
        return ResponseEntity.ok().body(noticeService.getNotice(noticeId));
    }

    @PatchMapping("/updateNotice")
    public ResponseEntity<?> updateNotice(@RequestParam("noticeId") Long noticeId, @RequestBody NoticeDto.NoticePatchDto noticePatchDto) {
        return ResponseEntity.ok().body(noticeService.updateNotice(noticeId, noticePatchDto));
    }

    @DeleteMapping("/deleteNotice")
    public ResponseEntity<?> deleteNotice(@RequestParam("noticeId") long noticeId) {
        noticeService.deleteNotice(noticeId);
        return ResponseEntity.ok().body("삭제된 NoticeId: " + noticeId);
    }

    @PostMapping(value = "/updateNoticeImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNoticeImages(@RequestParam Long noticeId, @RequestPart List<MultipartFile> imageList) {
        return ResponseEntity.ok().body(noticeService.updateNoticeImages(noticeId, imageList));
    }

    /**
     * 검색 기능
     */

    @GetMapping("/search")
    public ResponseEntity<Page<NoticeDto.NoticeResponseDto>> searchNoticeByKeyword(
            @RequestParam String keyword,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<NoticeDto.NoticeResponseDto> searchResultPage = noticeService.searchNoticeByKeyword(keyword, pageable);
        return ResponseEntity.ok().body(searchResultPage);
    }




}
