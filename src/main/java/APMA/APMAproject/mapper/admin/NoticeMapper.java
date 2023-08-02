package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.constant.NoticeType;
import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.NoticeDto;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,// 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface NoticeMapper {


    @Mapping(source = "admin.id", target = "adminId")
    NoticeDto.NoticeResponseDto toResponseDto(NoticeEntity noticeEntity);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "images", ignore = true),
    })
    NoticeEntity toRequestEntity(NoticeDto.NoticeRequestDto noticeRequestDto, AdminEntity admin);


    /**
     * 업데이트
     */

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "admin", ignore = true),
            @Mapping(target = "images", ignore = true),
    })
    public void updateFromPatchDto(NoticeDto.NoticePatchDto noticePatchDto, @MappingTarget NoticeEntity noticeEntity);


}
