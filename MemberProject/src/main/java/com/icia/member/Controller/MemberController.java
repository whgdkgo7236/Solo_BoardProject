package com.icia.member.Controller;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor//final 키워드가 붙은 필드만으로 생성자를 만들어줌
public class MemberController {

    private final MemberService ms;


    @GetMapping("save")
    public String saveForm(Model model){
        model.addAttribute("member",new MemberSaveDTO());

        return "/member/save";
    }
    @PostMapping("save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){
        System.out.println("memberSaveDTO = " + memberSaveDTO);

        if(bindingResult.hasErrors()){
            return "member/save";
        }
        try{
            ms.save(memberSaveDTO);
        }catch (IllegalStateException e){
            // e.getMessage()에는 서비스에서 지정한 예외메시지가 담겨있다.
            bindingResult.reject("emailCheck",e.getMessage());
            return "member/save";
        }

        return "/member/login";
    }
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("login", new MemberLoginDTO());
        return "/member/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult,
                        HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        boolean loginResult=ms.login(memberLoginDTO);
        if(loginResult){
            session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());
            return "redirect:/member/findAll";
        }else{
            //로그인결과에 글로벌 오류
            bindingResult.reject("loginFail","이메일 또는 비밀번호가 틀립니다.");
            return "member/login";
        }

    }

    //상세조회
    // /member/2, /member/15
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model){
        System.out.println("memberId= "+memberId);
        MemberDetailDTO member = ms.findById(memberId);
        return "member/detail";
    }

    //목록출력(/member)
    @GetMapping
    public String findAll(Model model){
        List<MemberDetailDTO> memberList  =ms.findAll();
        model.addAttribute("memberList",memberList);
        return "member/findAll";
    }


}
