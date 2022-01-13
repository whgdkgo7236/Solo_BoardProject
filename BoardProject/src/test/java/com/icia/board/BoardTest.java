package com.icia.board;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest

public class BoardTest {
    @Autowired
    BoardService bs;

    @Test
    @DisplayName("게시판 데이터 생성")
    public void newBoard(){
        //intStream을 이용하여 새글 30개 DB에 저장
        IntStream.rangeClosed(1,30).forEach(i ->{
            bs.save(new BoardSaveDTO("작성자"+i,"비밀번호"+i,"제목"+i,"내용"+i));
        });

    }
}
