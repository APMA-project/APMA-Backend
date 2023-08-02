package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.AdminEntity.AdminEntityBuilder;
import APMA.APMAproject.dto.admin.AdminDto.AdminPatchDto;
import APMA.APMAproject.dto.admin.AdminDto.AdminResponseDto;
import APMA.APMAproject.dto.admin.AdminDto.AdminResponseDto.AdminResponseDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T12:08:28+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminResponseDto toResponseDto(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminResponseDtoBuilder adminResponseDto = AdminResponseDto.builder();

        adminResponseDto.id( adminEntity.getId() );

        return adminResponseDto.build();
    }

    @Override
    public AdminEntity toResponseEntity(AdminResponseDto adminResponseDto) {
        if ( adminResponseDto == null ) {
            return null;
        }

        AdminEntityBuilder adminEntity = AdminEntity.builder();

        adminEntity.id( adminResponseDto.getId() );

        return adminEntity.build();
    }

    @Override
    public void updateFromPatchDto(AdminPatchDto AdminPatchDto, AdminEntity adminEntity) {
        if ( AdminPatchDto == null ) {
            return;
        }

        if ( AdminPatchDto.getPassword() != null ) {
            adminEntity.setPassword( AdminPatchDto.getPassword() );
        }
    }
}
