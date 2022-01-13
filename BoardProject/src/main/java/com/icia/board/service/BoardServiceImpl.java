package com.icia.board.service;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        return br.save(BoardEntity.saveBoard(boardSaveDTO)).getId();
    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardDetailDTO = new ArrayList<>();
        for(BoardEntity m: boardEntityList){
            boardDetailDTO.add(BoardDetailDTO.toBoardEntity(m));
        }
        System.out.println("findAll service = "+boardDetailDTO);
        return boardDetailDTO;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        /*
         Optional 객체 매서드
          - isPresent(): 데이터가 잇으면 true, 없으면 false 반환
          - isEmpty() : 데이터가 없으면 true , 있으면 false 반환
          -get(): Optional 객체에 들어있는 실제 데이타를 가져올때
         */
        BoardDetailDTO boardDetailDTO=null;
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardEntity(boardEntity);
        }
        return boardDetailDTO;
    }
}
