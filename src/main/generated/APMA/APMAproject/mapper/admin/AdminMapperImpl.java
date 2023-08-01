package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.AdminEntity.AdminEntityBuilder;
import APMA.APMAproject.dto.admin.AdminDto.AdminResponseDto;
import APMA.APMAproject.dto.admin.AdminDto.AdminResponseDto.AdminResponseDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T10:09:12+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminResponseDto toAdminResponseDto(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminResponseDtoBuilder adminResponseDto = AdminResponseDto.builder();

        adminResponseDto.id( adminEntity.getId() );
        adminResponseDto.username( adminEntity.getUsername() );

        return adminResponseDto.build();
    }

    @Override
    public AdminEntity toAdminEntity(AdminResponseDto adminResponseDto) {
        if ( adminResponseDto == null ) {
            return null;
        }

        AdminEntityBuilder adminEntity = AdminEntity.builder();

        adminEntity.id( adminResponseDto.getId() );
        adminEntity.username( adminResponseDto.getUsername() );

        return adminEntity.build();
    }
}
