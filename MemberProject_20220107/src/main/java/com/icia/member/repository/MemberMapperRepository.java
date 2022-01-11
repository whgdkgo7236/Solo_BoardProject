package com.icia.member.repository;


import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberMapperDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapperRepository {
    //회원목록 출력
    List<MemberMapperDTO> memberList();

}
