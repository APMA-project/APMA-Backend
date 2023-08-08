package APMA.APMAproject.controller.admin;


import APMA.APMAproject.config.spring_security.auth.PrincipalDetails;
import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("APMA/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getAdmin")
    public ResponseEntity<?> getAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(adminService.getAdmin(principalDetails.getUser().getId())); //todo : adminService.getAdmin(adminId)의 리턴값을 body에 담아 리턴해야함
    }

//    @GetMapping("/getAdmin")
//    public ResponseEntity<?> getAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails){
//        return ResponseEntity.ok().body(adminService.getAdmin(principalDetails.getUser().getId()));
//    }

    @PatchMapping("/updateAdmin")
    public ResponseEntity<?> updateAdmin(@RequestParam("adminId") Long adminId, @RequestBody AdminDto.AdminPatchDto adminPatchDto) {
        return ResponseEntity.ok().body(adminService.updateAdmin(adminId, adminPatchDto));
    }

    @DeleteMapping("/deleteAdmin")
    public ResponseEntity<?> deleteAdmin(@RequestParam("adminId") long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok().body("삭제된 MemberId: " + adminId);
    }
}
