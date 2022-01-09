package com.icia.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor //모든 필드를 매게변수로하는 생성자 lombook
@NoArgsConstructor // 기본생성자를 만들어주는 lombook
public class MemberSaveDTO {
    @NotBlank(message="이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank
    private String memberPassword;

    private String memberName;


}
