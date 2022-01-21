package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.MemberSaveDTO;
import com.icia.solo_boardproject.entity.MemberEntity;
import com.icia.solo_boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile m_file = memberSaveDTO.getM_file();

        String m_filename = m_file.getOriginalFilename();
        memberSaveDTO.setOrigFilename(m_filename);
        m_filename = System.currentTimeMillis()+"-"+m_filename;
        memberSaveDTO.setM_filename(m_filename);
        String savePath = "C:\\code\\imagedownloads\\"+memberSaveDTO.getOrigFilename();
        memberSaveDTO.setFilePath(savePath);
        if(!m_file.isEmpty()){
            m_file.transferTo(new File(savePath));
        }
        memberSaveDTO.setM_filename(m_filename);
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberSaveDTO);
        long memberId=mr.save(memberEntity).getId();
        return memberId;
    }
}
