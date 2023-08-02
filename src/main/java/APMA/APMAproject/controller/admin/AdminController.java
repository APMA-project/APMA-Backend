package APMA.APMAproject.controller.admin;


import APMA.APMAproject.dto.admin.AdminDto;
import APMA.APMAproject.dto.member.MemberDto;
import APMA.APMAproject.service.admin.AdminService;
import APMA.APMAproject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getAdmin")
    public ResponseEntity<?> getAdmin(@RequestParam("adminId") Long adminId) {
        // Assume memberId is provided as a request parameter
        adminService.getAdmin(adminId);
        return ResponseEntity.ok().body("조회된 MemberId: " + adminId);
    }

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
