package APMA.APMAproject.mapper.member;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,// 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface MemberMapper {


    /**
     * Entity -> Dto
     */

    MemberDto.MemberResponseDto toResponseDto(MemberEntity memberEntity);

    MemberDto.MemberRequestDto toReqeustDto(MemberEntity memberEntity);

    /**
     * Dto -> Entity
     */

    @Mapping(target = "password", ignore = true)
    MemberEntity toResponseEntity(MemberDto.MemberResponseDto memberResponseDto);
    MemberEntity toRequestEntity(MemberDto.MemberRequestDto memberRequestDto);

    /**
     * 업데이트
     *
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id", ignore = true)
    void updateFromPatchDto(MemberDto.MemberPatchDto memberPatchDto, @MappingTarget MemberEntity memberEntity);

}