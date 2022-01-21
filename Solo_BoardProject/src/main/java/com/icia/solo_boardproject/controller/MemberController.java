package com.icia.solo_boardproject.controller;

import com.icia.solo_boardproject.dto.MemberSaveDTO;
import com.icia.solo_boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm(){
        return "/member/save";
    }
    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws IOException {
        System.out.println("memberSaveDTO = " + memberSaveDTO);
        Long memberId=ms.save(memberSaveDTO);
        return "index";
    }
    @GetMapping("login")
    public String loginForm(){
     return "/member/login";
    }

}
