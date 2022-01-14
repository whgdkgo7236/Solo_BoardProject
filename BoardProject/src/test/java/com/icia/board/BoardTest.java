package com.icia.board;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest

public class BoardTest {
    @Autowired
    BoardService bs;

    @Autowired
    BoardRepository br;

    @Test
    @DisplayName("게시판 데이터 생성")
    public void newBoard(){
        //intStream을 이용하여 새글 30개 DB에 저장
        IntStream.rangeClosed(1,30).forEach(i ->{
            bs.save(new BoardSaveDTO("작성자"+i,"비밀번호"+i,"제목"+i,"내용"+i));
        });

    }
    @Test
    @DisplayName("삼항연산자")
    public void test1(){
        int num = 10;
        int num2 =10;
        if(num == 10){
            num2 = 5;
        }else{
            num2 = 100;
        }
        num2 =(num==10)? 5:100;   // = 조건식 ? true:false   삼항연산자

    }
    @Test
    @Transactional
    @DisplayName("페이징테스트")
    public void pagingtest(){
        int page = 5;
        Page<BoardEntity> boardEntityPage =br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        //Page 객체가 제공해주는 메서드 확인
        System.out.println("boardEntities.getContent() =" +boardEntityPage.getContent());//요청페이지에 들어잇는주소값
        System.out.println("boardEntities.getTotalElements() =" +boardEntityPage.getTotalElements());//전체 글갯수
        System.out.println("boardEntities.getNumber() =" +boardEntityPage.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardEntities.getTotalPages() =" +boardEntityPage.getTotalPages());//전체 페이지 갯수
        System.out.println("boardEntities.getSize() =" +boardEntityPage.getSize());//한페이지의 보여지는 글갯수
        System.out.println("boardEntities.HasPrevious() =" +boardEntityPage.hasPrevious());//이전페이지 존재 여부
        System.out.println("boardEntities.isFirst() =" +boardEntityPage.isFirst());// 첫페이지인지 여부
        System.out.println("boardEntities.isLast() =" +boardEntityPage.isLast());//마지막페이지인지 여부

        //Page<BoardEntity> -> Page<BoardPaging>
        //map(): 엔티티가 담긴 페이지를 객체를 dto가 담긴페이지객체로 변환해주는 역활

        Page<BoardPagingDTO> boardList = boardEntityPage.map(
                board -> new BoardPagingDTO(board.getId(),
                                            board.getBoardWriter(),
                                            board.getBoardTitle())
                         );
        System.out.println("boardList.getContent() =" +boardList.getContent());//요청페이지에 들어잇는주소값
        System.out.println("boardList.getTotalElements() =" +boardList.getTotalElements());//전체 글갯수
        System.out.println("boardList.getNumber() =" +boardList.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardList.getTotalPages() =" +boardList.getTotalPages());//전체 페이지 갯수
        System.out.println("boardList.getSize() =" +boardList.getSize());//한페이지의 보여지는 글갯수
        System.out.println("boardList.HasPrevious() =" +boardList.hasPrevious());//이전페이지 존재 여부
        System.out.println("boardList.isFirst() =" +boardList.isFirst());// 첫페이지인지 여부
        System.out.println("boardList.isLast() =" +boardList.isLast());//마지막페이지인지 여부

    }
}
