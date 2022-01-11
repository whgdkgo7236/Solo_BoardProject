package com.icia.member.controller;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.icia.member.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor //final이 붙은 생성자들로만들어준다
public class MemberController {

    private final MemberService ms;
    //회원가입폼
    @GetMapping("save")
    public String saveForm(){
        System.out.println("saveform");
        return "member/save";
    }
    //회원가입
    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO){
        Long memberId = ms.save(memberSaveDTO);
       
        return "member/login";
    }

    //login폼
    @GetMapping("login")
    public String loginForm(){

        return "member/login";
    }
    //login
    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session,Model model,
                        @RequestParam(defaultValue = "/") String redirectURL){
        System.out.println("MemberController.login");
        System.out.println("rediretURL = "+ redirectURL);
        boolean loginResult =ms.login(memberLoginDTO);
        if(loginResult){
            session.setAttribute(LOGIN_EMAIL,memberLoginDTO.getMemberEmail());

//            return "redirect:/member/";
            return "redirect:"+redirectURL; //사용자가 요청한 주소로 다시보내준다.
        }else{
            return "member/login";
        }

    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
    //회원목록
    @GetMapping
    public String findAll(Model model){

        List<MemberDetailDTO> memberList=ms.findAll();

        System.out.println(memberList);
        model.addAttribute("memberList",memberList);
        return "member/findAll";
    }

    // 회원조회(/member/5)
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model){
       MemberDetailDTO member = ms.findById(memberId);
       model.addAttribute("member",member);
       return "member/findById";
    }

    //회원조회(ajax)
    @PostMapping("{memberId}")
    public @ResponseBody MemberDetailDTO detail(@PathVariable("memberId") Long memberId){
        MemberDetailDTO member = ms.findById(memberId);
        return member;

    }

    // 회원삭제 (/member/delete/5)
    @GetMapping("delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId){
        ms.deleteById(memberId);
        return "redirect:/member/";
    }
    //회원삭제(/member/4)button
    @DeleteMapping("{memberId}")
    public ResponseEntity deleteById2(@PathVariable Long memberId){
        System.out.println(memberId);

        ms.deleteById(memberId);

        return new ResponseEntity(HttpStatus.OK);
    }
    //update
    @GetMapping("update")
    public String updateForm(Model model,HttpSession session){
        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO member =ms.findByEmail(memberEmail);
        model.addAttribute("member",member);
        return "member/update";
    }

    //수정처리
    @PostMapping("update")
    public String update(@ModelAttribute MemberDetailDTO memberDetailDTO){
        System.out.println(memberDetailDTO);
        Long memberId=ms.update(memberDetailDTO);
        //수정완료후 해당회원의 상세페이지 출력

        return "redirect:/member/"+memberDetailDTO.getMemberId();
        //왜 리다이랙트까지 필요한거지?
    }
    //수정처리(put)
    @PutMapping("{memberId}")
    // json 으로 데이터가 전달되면 @RequestBody로 받아줘야함.
    public ResponseEntity update2(@RequestBody MemberDetailDTO memberDetailDTO){
        /*
            // 단순 화면 출력이 아닌 데이터를 리턴하고자 할때 사용하는 리턴방식(restful을 할떄 많이사용)
            ResponseEntity : 데이터 & 상태코드(http상태코드 ex(200,404,405,500 등))를 함께 리턴할 수 있음.
            @ResponseBody : 데이터를 리턴할 수 있다.
         */
        // 200 코드를 리턴
        Long memberId = ms.update(memberDetailDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
