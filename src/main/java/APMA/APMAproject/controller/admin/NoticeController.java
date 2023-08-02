package APMA.APMAproject.controller.admin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.NoticeDto;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.service.admin.NotcieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
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

//    @GetMapping("/getAllNotices")
//    public ResponseEntity<List<NoticeDto.NoticeResponseDto>> getAllNotices() {
//        List<NoticeDto.NoticeResponseDto> noticeResponseDtos = noticeService.getAllNotice();
//        return ResponseEntity.ok().body(noticeResponseDtos);
//
//    }

}
