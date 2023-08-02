package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.dto.member.MemberDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,// 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface AdminMapper {

    /**
     * Entity -> Dto
     */
    AdminDto.AdminResponseDto toResponseDto(AdminEntity adminEntity);

    /**
     * Dto -> Entity
     */
    @Mapping(target ="password", ignore = true )
    AdminEntity toResponseEntity(AdminDto.AdminResponseDto adminResponseDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id", ignore = true)
    void updateFromPatchDto(AdminDto.AdminPatchDto AdminPatchDto, @MappingTarget AdminEntity adminEntity);
}
