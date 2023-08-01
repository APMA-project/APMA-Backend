package APMA.APMAproject.mapper.admin;

import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.admin.NoticeEntity.NoticeEntityBuilder;
import APMA.APMAproject.dto.admin.NoticeDto.NoticePatchDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeRequestDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeRequestDto.NoticeRequestDtoBuilder;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeResponseDto;
import APMA.APMAproject.dto.admin.NoticeDto.NoticeResponseDto.NoticeResponseDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T10:09:12+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class NoticeMapperImpl implements NoticeMapper {

    @Override
    public NoticeResponseDto toNoticeResponseDto(NoticeEntity noticeEntity) {
        if ( noticeEntity == null ) {
            return null;
        }

        NoticeResponseDtoBuilder noticeResponseDto = NoticeResponseDto.builder();

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
    public NoticeRequestDto toNoticeRequestDto(NoticeEntity noticeEntity) {
        if ( noticeEntity == null ) {
            return null;
        }

        NoticeRequestDtoBuilder noticeRequestDto = NoticeRequestDto.builder();

        noticeRequestDto.title( noticeEntity.getTitle() );
        noticeRequestDto.content( noticeEntity.getContent() );
        noticeRequestDto.noticeType( noticeEntity.getNoticeType() );

        return noticeRequestDto.build();
    }

    @Override
    public NoticeEntity toNoticeResponseEntity(NoticeResponseDto noticeResponseDto) {
        if ( noticeResponseDto == null ) {
            return null;
        }

        NoticeEntityBuilder noticeEntity = NoticeEntity.builder();

        noticeEntity.id( noticeResponseDto.getId() );
        noticeEntity.title( noticeResponseDto.getTitle() );
        noticeEntity.content( noticeResponseDto.getContent() );
        noticeEntity.noticeType( noticeResponseDto.getNoticeType() );
        List<String> list = noticeResponseDto.getImages();
        if ( list != null ) {
            noticeEntity.images( new ArrayList<String>( list ) );
        }

        return noticeEntity.build();
    }

    @Override
    public NoticeEntity toNoticeRequestEntity(NoticeRequestDto noticeRequestDto) {
        if ( noticeRequestDto == null ) {
            return null;
        }

        NoticeEntityBuilder noticeEntity = NoticeEntity.builder();

        noticeEntity.title( noticeRequestDto.getTitle() );
        noticeEntity.content( noticeRequestDto.getContent() );
        noticeEntity.noticeType( noticeRequestDto.getNoticeType() );

        return noticeEntity.build();
    }

    @Override
    public void updateFromPatchDto(NoticePatchDto noticePatchDto, NoticeEntity noticeEntity) {
        if ( noticePatchDto == null ) {
            return;
        }

        noticeEntity.setId( noticePatchDto.getId() );
        noticeEntity.setTitle( noticePatchDto.getTitle() );
        noticeEntity.setContent( noticePatchDto.getContent() );
        noticeEntity.setNoticeType( noticePatchDto.getNoticeType() );
        if ( noticeEntity.getImages() != null ) {
            List<String> list = noticePatchDto.getImages();
            if ( list != null ) {
                noticeEntity.getImages().clear();
                noticeEntity.getImages().addAll( list );
            }
            else {
                noticeEntity.setImages( null );
            }
        }
        else {
            List<String> list = noticePatchDto.getImages();
            if ( list != null ) {
                noticeEntity.setImages( new ArrayList<String>( list ) );
            }
        }
    }
}
