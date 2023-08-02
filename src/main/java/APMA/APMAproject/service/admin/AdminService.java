package APMA.APMAproject.service.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.mapper.admin.AdminMapper;
import APMA.APMAproject.repository.amin.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    /**
     * admin 회원가입 create x
     */

    public AdminDto.AdminResponseDto getAdmin(Long adminId) {
        // Entity 조회
        AdminEntity adminEntity = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId));

        // Entity를 DTO로 변환 후 return
        return adminMapper.toResponseDto(adminEntity);
    }
    @Transactional
    public AdminDto.AdminResponseDto updateAdmin(Long adminId,AdminDto.AdminPatchDto adminPatchDto) {

        AdminEntity adminEntity = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId));


        // MemberPatchDto에서 변경된 필드 MemberEntity에 반영
        adminMapper.updateFromPatchDto(adminPatchDto,adminEntity);

        // 엔티티 저장
        adminRepository.save(adminEntity); //todo : save 해주지 않아도 이미 persistence context에 객체가 존재하기 때문에 dirty checking 을 사용할 수 있음.


        return adminMapper.toResponseDto(adminEntity);
    }

    @Transactional
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
        log.info("삭제된 아이디: {}",adminId);
    }
}
