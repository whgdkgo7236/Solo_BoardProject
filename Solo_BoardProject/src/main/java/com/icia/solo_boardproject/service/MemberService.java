package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.MemberSaveDTO;

import java.io.IOException;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;
}
