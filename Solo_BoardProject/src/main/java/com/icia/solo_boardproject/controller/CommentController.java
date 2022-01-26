package com.icia.solo_boardproject.controller;

import com.icia.solo_boardproject.dto.CommentDetailDTO;
import com.icia.solo_boardproject.dto.CommentSaveDTO;
import com.icia.solo_boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService cs;

    @PostMapping("save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO){
        Long commentId =cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(commentId);
        return commentList;
    }
}
