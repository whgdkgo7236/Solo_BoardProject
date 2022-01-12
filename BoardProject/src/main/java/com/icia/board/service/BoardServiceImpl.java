package com.icia.board.service;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        return br.save(BoardEntity.saveBoard(boardSaveDTO)).getId();
    }
}
