package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.CommentDetailDTO;
import com.icia.solo_boardproject.dto.CommentSaveDTO;
import com.icia.solo_boardproject.entity.BoardEntity;
import com.icia.solo_boardproject.entity.CommentEntity;
import com.icia.solo_boardproject.repository.BoardRepository;
import com.icia.solo_boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository cr;
    private final BoardRepository br;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getId()).get();
        CommentEntity commentEntity=CommentEntity.toSaveEntity(commentSaveDTO,boardEntity);
        return cr.save(commentEntity).getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long commentId) {
        BoardEntity boardEntity=br.findById(commentId).get();
        return null;
    }
}
