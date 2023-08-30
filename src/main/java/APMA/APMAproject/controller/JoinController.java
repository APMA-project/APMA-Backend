package APMA.APMAproject.controller;

import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("APMA/join")
public class JoinController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<?> createMember(@RequestBody MemberDto.MemberRequestDto memberRequestDto) {
        //로그
        log.info("createMember 진입 !!");
        log.info("memberRequestDto의 username : " + memberRequestDto.getUsername());
        MemberDto.MemberResponseDto member = memberService.createMember(memberRequestDto);
        log.info("memberResponseDto의 username : " + member.getUsername());
        return ResponseEntity.ok().body(member);
    }
}
