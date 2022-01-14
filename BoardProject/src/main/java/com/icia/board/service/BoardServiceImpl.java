package com.icia.board.service;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = BoardEntity.updateBoard(boardUpdateDTO);
        br.save(boardEntity);
        return null;
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();   //페이징처리 JPA로처리할시 0번부터시작
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청페이지에서 -1을한다.
//            page = page -1;
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> boardEntities=br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));//"id"는 entity 필드이름!!언드바를인식못함
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle())
        );
        return boardList;
    }
}
