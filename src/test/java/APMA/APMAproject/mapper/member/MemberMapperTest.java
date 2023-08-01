package APMA.APMAproject.mapper.member;

import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.member.MemberDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {

    @Test  //Dto -> Entity
    public void testConvertDtoToEntity() {
        // Given
        MemberDto.MemberResponseDto dto = MemberDto.MemberResponseDto.builder()
                .id(1L)
                .username("john_doe")
                .email("john@example.com")
                .name("John Doe")
                .phoneNumber("123-456-7890")
                .build();

        // When
        MemberEntity entity = MemberEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        // Then
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getUsername(), entity.getUsername());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getPhoneNumber(), entity.getPhoneNumber());
    }

    @Test  //Entity -> Dto
    public void testConvertEntityToDto() {
        // Given
        MemberEntity entity = MemberEntity.builder()
                .id(1L)
                .username("john_doe")
                .email("john@example.com")
                .name("John Doe")
                .phoneNumber("123-456-7890")
                .build();

        // When
        MemberDto.MemberResponseDto dto = MemberDto.MemberResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .build();

        // Then
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getPhoneNumber(), dto.getPhoneNumber());
    }




}