package APMA.APMAproject.service.admin;

import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.dto.admin.AdminDto;
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
        return adminMapper.toAdminResponseDto(adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId)));
    }

    @Transactional
    public AdminDto.AdminResponseDto updateAdmin(Long adminId,AdminDto.AdminPatchDto adminPatchDto) {

        AdminEntity adminEntity = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId));


//        비밀번호 업데이트
//        if(adminPatchDto.getPassword()!=null) {}; 스프링 시큐리티 적용 후 수정

        //entity->dto
        return adminMapper.toAdminResponseDto(adminRepository.findById(adminId)
                .orElseThrow(()->new EntityNotFoundException("해당 ID에 해당하는 AdminEntity를 찾을 수 없음")));
    }

    @Transactional
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
        log.info("삭제된 아이디: {}",adminId);
    }







}
