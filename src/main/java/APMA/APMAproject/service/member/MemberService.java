package APMA.APMAproject.service.member;
import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.mapper.member.MemberMapper;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberDto.MemberResponseDto createMember(Long adminId, MemberDto.MemberRequestDto memberRequestDto) {

        AdminEntity adminEntity = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId));

        // RequestDto -> Entity
        MemberEntity memberEntity = memberMapper.toMemberRequestEntity(memberRequestDto);

        // DB에 Entity 저장
        MemberEntity savedMember = memberRepository.save(memberEntity);

        // Entity -> ResponseDto
        MemberDto.MemberResponseDto responseDto = memberMapper.toMemberResponseDto(savedMember);

        return responseDto;
    }


    public MemberDto.MemberResponseDto getMember(Long memberId) {
        // Entity 조회
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + memberId));

        // Entity를 DTO로 변환 후 return
        return memberMapper.toMemberResponseDto(memberEntity);
    }


    @Transactional
    public MemberDto.MemberResponseDto updateMember(Long memberId,MemberDto.MemberPatchDto memberPatchDto) {

        // Entity 조회
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + memberId));

        // Update
        if (memberPatchDto.getUsername() != null) {
            memberEntity.setUsername(memberPatchDto.getUsername());
        }
        if (memberPatchDto.getEmail() != null) {
            memberEntity.setEmail(memberPatchDto.getEmail());
        }
        if (memberPatchDto.getName() != null) {
            memberEntity.setName(memberPatchDto.getName());
        }
        if (memberPatchDto.getPhoneNumber() != null) {
            memberEntity.setPhoneNumber(memberPatchDto.getPhoneNumber());
        }
//        if (memberPatchDto.getPassword() != null) {
//
//            memberEntity.setPassword();
//        } 스프링 시큐리티 적용 후 수정

        // entity->dto 후 return
        return memberMapper.toMemberResponseDto(memberEntity);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
        log.info("삭제된 아이디: {}",memberId);
    }

    @Transactional
    public List<MemberDto.MemberResponseDto> getAllMember(){

        List<MemberEntity> memberEntities = memberRepository.getAllMember();
        List<MemberDto.MemberResponseDto> memberResponseDtos = new ArrayList<>();

        for (MemberEntity memberEntity : memberEntities){
            MemberDto.MemberResponseDto memberResponseDto = memberMapper.toMemberResponseDto(memberEntity);
            memberResponseDtos.add(memberResponseDto);
        }

        return memberResponseDtos;
    }


}
