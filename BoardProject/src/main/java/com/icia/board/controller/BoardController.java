package com.icia.board.controller;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j // 로그를 기록할수있는 라이브러리
public class BoardController {
    private final BoardService bs;


    @GetMapping("save")
    public String saveForm(){
        return "/board/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO, Model model){
        Long memberId = bs.save(boardSaveDTO);
        return "index";
    }
/*
    @GetMapping()
    public String findAll(Model model){
        List<BoardDetailDTO> board_list = bs.findAll();
        System.out.println("findall = "+board_list);
        model.addAttribute("boardList",board_list);
        log.info("findAll 호출");
        return "board/findAll";
    }
*/

    //페이징 처리(/board?page=5) -> 쿼리스트링 방식
    //5번글(/board/5)
    @GetMapping()
    public String paging(@PageableDefault(page = 1) Pageable pageable,Model model){//pageable-> 자동완성기능 springframeword.data.domain
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "/board/paging";
    }

    @GetMapping("{boardId}")
    public String findById(Model model,@PathVariable Long boardId){
        log.info("글보기 메서드 호출 요청글번호: {}", boardId);//{} 자리에 , 변수가 찍힌다.
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/findById";
    }

    @PostMapping("/{boardId}")
    public ResponseEntity findById(@PathVariable Long boardId){
        BoardDetailDTO board = bs.findById(boardId);
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);
    }

    @PostMapping("update/{boardId}")
    public String updateForm(@PathVariable Long boardId,Model model){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO){
        bs.update(boardUpdateDTO);
        return "redirect:/board/"+boardUpdateDTO.getBoardId();
    }

    @PutMapping("/{boardId}")
    public ResponseEntity update2(@RequestBody BoardUpdateDTO boardUpdateDTO){
        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);

    }


}
