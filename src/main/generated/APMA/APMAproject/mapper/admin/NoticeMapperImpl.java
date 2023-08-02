package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.admin.NoticeEntity.NoticeEntityBuilder;
import APMA.APMAproject.dto.admin.NoticeDto.NoticePatchDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeRequestDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeResponseDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeResponseDto.NoticeResponseDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T12:08:28+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class NoticeMapperImpl implements NoticeMapper {

    @Override
    public NoticeResponseDto toResponseDto(NoticeEntity noticeEntity) {
        if ( noticeEntity == null ) {
            return null;
        }

        NoticeResponseDtoBuilder noticeResponseDto = NoticeResponseDto.builder();

        noticeResponseDto.adminId( noticeEntityAdminId( noticeEntity ) );
        noticeResponseDto.id( noticeEntity.getId() );
        noticeResponseDto.title( noticeEntity.getTitle() );
        noticeResponseDto.content( noticeEntity.getContent() );
        noticeResponseDto.noticeType( noticeEntity.getNoticeType() );
        List<String> list = noticeEntity.getImages();
        if ( list != null ) {
            noticeResponseDto.images( new ArrayList<String>( list ) );
        }

        return noticeResponseDto.build();
    }

    @Override
    public NoticeEntity toRequestEntity(NoticeRequestDto noticeRequestDto, AdminEntity admin) {
        if ( noticeRequestDto == null && admin == null ) {
            return null;
        }

        NoticeEntityBuilder noticeEntity = NoticeEntity.builder();

        if ( noticeRequestDto != null ) {
            noticeEntity.title( noticeRequestDto.getTitle() );
            noticeEntity.content( noticeRequestDto.getContent() );
            noticeEntity.noticeType( noticeRequestDto.getNoticeType() );
        }
        if ( admin != null ) {
            noticeEntity.admin( admin );
        }

        return noticeEntity.build();
    }

    @Override
    public void updateFromPatchDto(NoticePatchDto noticePatchDto, NoticeEntity noticeEntity) {
        if ( noticePatchDto == null ) {
            return;
        }

        if ( noticePatchDto.getTitle() != null ) {
            noticeEntity.setTitle( noticePatchDto.getTitle() );
        }
        if ( noticePatchDto.getContent() != null ) {
            noticeEntity.setContent( noticePatchDto.getContent() );
        }
        if ( noticePatchDto.getNoticeType() != null ) {
            noticeEntity.setNoticeType( noticePatchDto.getNoticeType() );
        }
    }

    private Long noticeEntityAdminId(NoticeEntity noticeEntity) {
        if ( noticeEntity == null ) {
            return null;
        }
        AdminEntity admin = noticeEntity.getAdmin();
        if ( admin == null ) {
            return null;
        }
        Long id = admin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
