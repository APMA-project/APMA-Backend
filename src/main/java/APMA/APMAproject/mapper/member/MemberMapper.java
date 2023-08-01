package APMA.APMAproject.mapper.member;

import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        unmappedTargetPolicy = ReportingPolicy.IGNORE // 일치하지 않는 필드 무시
)
public interface MemberMapper {

    /**
     * Entity -> Dto
     */
    MemberDto.MemberResponseDto toMemberResponseDto(MemberEntity memberEntity);

    MemberDto.MemberRequestDto toMemberReqeustDto(MemberEntity memberEntity);

    /**
     * Dto -> Entity
     */
    MemberEntity toMemberResponseEntity(MemberDto.MemberResponseDto memberResponseDto);
    MemberEntity toMemberRequestEntity(MemberDto.MemberRequestDto memberRequestDto);
}