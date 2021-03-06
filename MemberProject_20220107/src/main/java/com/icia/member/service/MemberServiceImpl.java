package com.icia.member.service;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private  final MemberRepository mr;
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        //JpaRepository는 무조건 Entity만 받음.

        //MemberSaveDTO =? MemberEntity
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        Long memberId = mr.save(memberEntity).getId();
        return memberId;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if(memberEntity !=null){
            if(memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }


    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();

        List<MemberDetailDTO> memberList = new ArrayList<>();
        for(MemberEntity m:memberEntityList){
            memberList.add(MemberDetailDTO.toMemberDetailDTO(m));
        }

        return memberList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        Optional<MemberEntity> memberEntityOptional =mr.findById(memberId);
        MemberEntity memberEntity =memberEntityOptional.get();
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
        return memberDetailDTO;
    }

    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

    @Override
    public MemberDetailDTO findByEmail(String memberEmail) {
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(mr.findByMemberEmail(memberEmail));
        return memberDetailDTO;
    }

    @Override
    public Long update(MemberDetailDTO memberDetailDTO) {
        //update 처리시 save 메서드 호출
        //MemberDetailDTO -> MemberEntity
        MemberEntity memberEntity =MemberEntity.toupdateMember(memberDetailDTO);
        Long memberId=mr.save(memberEntity).getId();
        return memberId;
    }
}
