package APMA.APMAproject.service.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.mapper.admin.AdminMapper;
import APMA.APMAproject.repository.amin.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        /**
         * encoding
         */
        if(adminPatchDto.getPassword()!=null) adminPatchDto.setPassword(bCryptPasswordEncoder.encode(adminPatchDto.getPassword()));

        // MemberPatchDto에서 변경된 필드 MemberEntity에 반영
        adminMapper.updateFromPatchDto(adminPatchDto,adminEntity);

        return adminMapper.toResponseDto(adminEntity);
    }

    @Transactional
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
        log.info("삭제된 아이디: {}",adminId);
    }
}
