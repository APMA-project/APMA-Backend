package APMA.APMAproject.service.admin;

import APMA.APMAproject.config.S3.S3Service;
import APMA.APMAproject.config.spring_security.auth.PrincipalDetails;
import APMA.APMAproject.domain.admin.AdminEntity;
import APMA.APMAproject.domain.admin.NoticeEntity;
import APMA.APMAproject.domain.member.MemberEntity;
import APMA.APMAproject.dto.admin.NoticeDto;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.mapper.admin.NoticeMapper;
import APMA.APMAproject.repository.amin.AdminRepository;
import APMA.APMAproject.repository.amin.NoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class NotcieService {

    private final NoticeRepository noticeRepository;
    private final AdminRepository adminRepository;
    private final NoticeMapper noticeMapper;
    private final S3Service s3Service;



    @Transactional
    public NoticeDto.NoticeResponseDto createNotice(Long adminId, NoticeDto.NoticeRequestDto noticeRequestDto) {

        AdminEntity adminEntity = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 ID: " + adminId));


        NoticeEntity savedNotice = noticeRepository.save(noticeMapper.toRequestEntity(noticeRequestDto, adminEntity));
        NoticeDto.NoticeResponseDto responseDto = noticeMapper.toResponseDto(savedNotice);

        return responseDto;
    }

    public NoticeDto.NoticeResponseDto getNotice(Long noticeId) {

        return noticeMapper.toResponseDto(noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 Notice: " + noticeId)));

    }

    @Transactional
    public NoticeDto.NoticeResponseDto updateNotice(Long noticeId, NoticeDto.NoticePatchDto noticePatchDto) {

        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoSuchElementException("등록되지 않은 Notice: " + noticeId));

        noticeMapper.updateFromPatchDto(noticePatchDto,noticeEntity);

        return noticeMapper.toResponseDto(noticeEntity);
    }

    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
        log.info("삭제된 Notice: {}",noticeId);
    }

    @Transactional
    public List<String> updateNoticeImages(Long noticeId, List<MultipartFile> imageList) {
        NoticeEntity noticeEntity = noticeRepository.findById(noticeId)
                .orElseThrow(()->new NoSuchElementException("등록되지 않은 Notice: " + noticeId));

        //기존 이미지 삭제
        noticeEntity.getImages().forEach(s3Service::deleteFile);

        //새로운 이미지 업로드
        List<String> newImageUrls = s3Service.uploadFileList(imageList);
        noticeEntity.updateNoticeImages(newImageUrls);

        return newImageUrls;
    }

    @Transactional
    public Page<NoticeDto.NoticeResponseDto> searchNoticeByKeyword(String keyword, Pageable pageable) {

        Page<NoticeEntity> searchResultPage = noticeRepository.findKeyword(keyword, pageable);
        //Dto로 변환 후 Page 생성
        Page<NoticeDto.NoticeResponseDto> responseDtoPage = searchResultPage.map(noticeMapper::toResponseDto);
        return responseDtoPage;
    }

}
