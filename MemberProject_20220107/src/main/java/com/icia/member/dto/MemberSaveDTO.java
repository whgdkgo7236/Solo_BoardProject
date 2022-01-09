package com.icia.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveDTO {
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    /*
    @NoArgsConstructor
    public MemberSaveDTO(){
    }
    @AllArgsConstructor
    public MemberSaveDTO(String memberEmail, String memberPassword, String memberName){
    this.memberEmail =memberEmail
    this.memberPassword =memberPassword
    this.memberName =memberName
      }
     */
}
