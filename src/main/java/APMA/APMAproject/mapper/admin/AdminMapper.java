package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        unmappedTargetPolicy = ReportingPolicy.IGNORE // 일치하지 않는 필드 무시
)
public interface AdminMapper {

    /**
     * Entity -> Dto
     */
    AdminDto.AdminResponseDto toAdminResponseDto(AdminEntity adminEntity);

    /**
     * Dto -> Entity
     */
    AdminEntity toAdminEntity(AdminDto.AdminResponseDto adminResponseDto);

}
