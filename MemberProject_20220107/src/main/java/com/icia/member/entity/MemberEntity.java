package com.icia.member.entity;

import com.icia.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//db에따라 strategy 다름
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    @Column
    private String memberName;

    //MemberSaveDTO-> MemberEntity 객체로 변환하기위한 메서드
    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity = new MemberEntity();
        String memberEmail = memberSaveDTO.getMemberEmail();
        memberEntity.setMemberEmail(memberEmail);

        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());

        return memberEntity;
    }
}
