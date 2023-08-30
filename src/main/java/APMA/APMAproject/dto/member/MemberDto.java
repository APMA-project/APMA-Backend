package APMA.APMAproject.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.Locale;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberRequestDto {


        @NotBlank(message = "아이디를 작성해주세요.")
        private String username;

        @NotBlank(message = "이름을 작성해주세요.")
        private String name;

        //비밀번호 정규식. 8~15자 영문, 숫자, 특수문자 조합으로 이뤄져야한다.
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;


        //핸드폰번호 정규식. '-'가 있어도 되고 없어도 된다.
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
                message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String phoneNumber;


        @NotBlank(message = "yyyy-mm-dd 형식으로 작성해주세요.")
        private LocalDate birthDay;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberResponseDto {
        private Long id;

        private String username;

        private String name;

        private String phoneNumber;

        private String birthDay;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MemberPatchDto {

        @NotBlank(message = "아이디를 작성해주세요.")
        private String username;

        @NotBlank(message = "이름을 작성해주세요.")
        private String name;

        //비밀번호 정규식. 8~15자 영문, 숫자, 특수문자 조합으로 이뤄져야한다.
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;


        //핸드폰번호 정규식. '-'가 있어도 되고 없어도 된다.
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
                message = "휴대폰 번호를 정확하게 입력해주세요.")
        private String phoneNumber;

        @NotBlank(message = "yyyy-mm-dd 형식으로 작성해주세요.")
        private LocalDate birthDay;

    }




}
