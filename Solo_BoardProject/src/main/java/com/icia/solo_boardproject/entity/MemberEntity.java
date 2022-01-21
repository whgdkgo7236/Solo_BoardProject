package com.icia.solo_boardproject.entity;

import com.icia.solo_boardproject.dto.MemberSaveDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "membertable")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberPhonenum;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @Builder
    public void File(Long id,String origFilename, String filename,String filePath){
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public static MemberEntity toSaveEntity(MemberSaveDTO memberSaveDTO){
        MemberEntity entity = new MemberEntity();
        entity.setMemberEmail(memberSaveDTO.getM_email());
        entity.setMemberName(memberSaveDTO.getM_name());
        entity.setMemberPassword(memberSaveDTO.getM_password());
        entity.setMemberPhonenum(memberSaveDTO.getM_phonenum());
        entity.setFilename(memberSaveDTO.getM_filename());
        entity.setFilePath(memberSaveDTO.getFilePath());
        entity.setOrigFilename(memberSaveDTO.getOrigFilename());

        return  entity;
    }
}
