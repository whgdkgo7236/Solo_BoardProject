package com.icia.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveDTO {
    @NotBlank(message="이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank
    private String memberPassword;
    private String memberName;

}
