package APMA.APMAproject.dto.admin;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

public class AdminDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminResponseDto {
        private Long id;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminPatchDto {


        //비밀번호 정규식. 8~15자 영문, 숫자, 특수문자 조합으로 이뤄져야한다.
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;
    }


}
