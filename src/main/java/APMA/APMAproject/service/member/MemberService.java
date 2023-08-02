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
    public MemberDto.MemberResponseDto createMember(MemberDto.MemberRequestDto memberRequestDto) {

        // RequestDto -> Entity
        MemberEntity memberEntity = memberMapper.toRequestEntity(memberRequestDto);

        // DB에 Entity 저장
        MemberEntity savedMember = memberRepository.save(memberEntity);

        // Entity -> ResponseDto
        MemberDto.MemberResponseDto responseDto = memberMapper.toResponseDto(savedMember);

        return responseDto;
    }


    public MemberDto.MemberResponseDto getMember(Long memberId) {
        // Entity 조회
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + memberId));

        // Entity를 DTO로 변환 후 return
        return memberMapper.toResponseDto(memberEntity);
    }


    @Transactional
    public MemberDto.MemberResponseDto updateMember(Long memberId,MemberDto.MemberPatchDto memberPatchDto) {

        // Entity 조회
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + memberId));

        // MemberPatchDto에서 변경된 필드 MemberEntity에 반영
        memberMapper.updateFromPatchDto(memberPatchDto,memberEntity);


        return memberMapper.toResponseDto(memberEntity);
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
            MemberDto.MemberResponseDto memberResponseDto = memberMapper.toResponseDto(memberEntity);
            memberResponseDtos.add(memberResponseDto);
        }

        return memberResponseDtos;
    }


}
