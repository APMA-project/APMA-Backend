package APMA.APMAproject.controller.member;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/createMember")
    public ResponseEntity<?> createMember(@RequestBody MemberDto.MemberRequestDto memberRequestDto) {
       return ResponseEntity.ok().body(memberService.createMember(memberRequestDto));
    }

    @GetMapping("/getMember")
    public ResponseEntity<?> getMember (@RequestParam("memberId") Long memberId) {
        // Assume memberId is provided as a request parameter
        memberService.getMember(memberId);
        return ResponseEntity.ok().body("조회된 MemberId: " + memberId);
    }

    @PatchMapping("/updateMember")
    public ResponseEntity<?> updateMember(@RequestParam("memberId") Long memberId, @RequestBody MemberDto.MemberPatchDto memberPatchDto) {
        return ResponseEntity.ok().body(memberService.updateMember(memberId, memberPatchDto));
    }

    @DeleteMapping("/deleteMember")
    public ResponseEntity<?> deleteMember(@RequestParam("memberId") long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().body("삭제된 MemberId: " + memberId);
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<MemberDto.MemberResponseDto>> getAllMembers() {
        List<MemberDto.MemberResponseDto> memberResponseDtos = memberService.getAllMember();
        if (!memberResponseDtos.isEmpty()) {
            return ResponseEntity.ok().body(memberResponseDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
