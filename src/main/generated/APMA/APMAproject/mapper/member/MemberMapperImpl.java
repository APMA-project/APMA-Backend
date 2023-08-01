package APMA.APMAproject.mapper.member;

import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.domain.member.MemberEntity.MemberEntityBuilder;
import APMA.APMAproject.dto.member.MemberDto.MemberRequestDto;
import APMA.APMAproject.dto.member.MemberDto.MemberRequestDto.MemberRequestDtoBuilder;
import APMA.APMAproject.dto.member.MemberDto.MemberResponseDto;
import APMA.APMAproject.dto.member.MemberDto.MemberResponseDto.MemberResponseDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T10:09:12+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponseDto toMemberResponseDto(MemberEntity memberEntity) {
        if ( memberEntity == null ) {
            return null;
        }

        MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        memberResponseDto.id( memberEntity.getId() );
        memberResponseDto.username( memberEntity.getUsername() );
        memberResponseDto.email( memberEntity.getEmail() );
        memberResponseDto.name( memberEntity.getName() );
        memberResponseDto.phoneNumber( memberEntity.getPhoneNumber() );

        return memberResponseDto.build();
    }

    @Override
    public MemberRequestDto toMemberReqeustDto(MemberEntity memberEntity) {
        if ( memberEntity == null ) {
            return null;
        }

        MemberRequestDtoBuilder memberRequestDto = MemberRequestDto.builder();

        memberRequestDto.username( memberEntity.getUsername() );
        memberRequestDto.email( memberEntity.getEmail() );
        memberRequestDto.name( memberEntity.getName() );
        memberRequestDto.password( memberEntity.getPassword() );
        memberRequestDto.phoneNumber( memberEntity.getPhoneNumber() );

        return memberRequestDto.build();
    }

    @Override
    public MemberEntity toMemberResponseEntity(MemberResponseDto memberResponseDto) {
        if ( memberResponseDto == null ) {
            return null;
        }

        MemberEntityBuilder memberEntity = MemberEntity.builder();

        memberEntity.id( memberResponseDto.getId() );
        memberEntity.username( memberResponseDto.getUsername() );
        memberEntity.name( memberResponseDto.getName() );
        memberEntity.email( memberResponseDto.getEmail() );
        memberEntity.phoneNumber( memberResponseDto.getPhoneNumber() );

        return memberEntity.build();
    }

    @Override
    public MemberEntity toMemberRequestEntity(MemberRequestDto memberRequestDto) {
        if ( memberRequestDto == null ) {
            return null;
        }

        MemberEntityBuilder memberEntity = MemberEntity.builder();

        memberEntity.username( memberRequestDto.getUsername() );
        memberEntity.name( memberRequestDto.getName() );
        memberEntity.email( memberRequestDto.getEmail() );
        memberEntity.password( memberRequestDto.getPassword() );
        memberEntity.phoneNumber( memberRequestDto.getPhoneNumber() );

        return memberEntity.build();
    }
}
