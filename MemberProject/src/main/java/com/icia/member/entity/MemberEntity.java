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
    @Id /* @Id pk 만든다는뜻*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50,unique = true, name= "memberEmail")
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    private String memberName;

    /*
    *   DTO클래스 객체를 전달받아 Entity클래스 필드값으로 세팅하고
    *   entity 객체를 리턴하는 메서드선언
    *
    *   static 메서드(정적메서드)사용 : 클래스 메서드, 객체를 만들지 않고도 바로 호출 가능.
   * */

    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        return memberEntity;
    }

}
