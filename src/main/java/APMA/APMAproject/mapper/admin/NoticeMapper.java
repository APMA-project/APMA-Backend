package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.NoticeDto;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        unmappedTargetPolicy = ReportingPolicy.IGNORE // 일치하지 않는 필드 무시
)
public interface NoticeMapper {

    /**
     * Entity -> Dto
     */
    NoticeDto.NoticeResponseDto toNoticeResponseDto(NoticeEntity noticeEntity);

    NoticeDto.NoticeRequestDto toNoticeRequestDto(NoticeEntity noticeEntity);

    /**
     * Dto -> Entity
     */
    NoticeEntity toNoticeResponseEntity(NoticeDto.NoticeResponseDto noticeResponseDto);
    NoticeEntity toNoticeRequestEntity(NoticeDto.NoticeRequestDto noticeRequestDto);

    public void updateFromPatchDto(NoticeDto.NoticePatchDto noticePatchDto, @MappingTarget NoticeEntity noticeEntity);


}
