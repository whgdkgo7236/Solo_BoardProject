package com.icia.solo_boardproject.service;

import com.icia.solo_boardproject.dto.BoardDetailDTO;
import com.icia.solo_boardproject.dto.BoardPagingDTO;
import com.icia.solo_boardproject.dto.MemberPagingDTO;
import com.icia.solo_boardproject.entity.BoardEntity;
import com.icia.solo_boardproject.entity.MemberEntity;
import com.icia.solo_boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    public static  final int PAGE_LIMIT = 3;
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();   //페이징처리 JPA로처리할시 0번부터시작
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청페이지에서 -1을한다.
//            page = page -1;
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> boardEntities=br.findAll(PageRequest.of(page, PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));//"id"는 entity 필드이름!!언드바를인식못함
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardHits())
        );
        System.out.println("boardService = "+ boardList);
        return boardList;
    }

    @Override
    public BoardDetailDTO findById(Long boardid) {
         BoardEntity boardEntity= br.findById(boardid).get();
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toboardDetail(boardEntity);
        System.out.println("repository = " + boardEntity.getId());
        return boardDetailDTO;
    }

    @Override
    public void deleteById(Long boardid) {
        br.deleteById(boardid);
    }
}
