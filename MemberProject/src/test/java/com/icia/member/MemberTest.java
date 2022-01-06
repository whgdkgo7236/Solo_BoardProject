package com.icia.member;

import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
public class MemberTest {
    /*
        MemberServiceImpl.save() 메서드가 잘 동작하는지를 테스트
     */
    @Autowired
    private MemberService ms;

    @Test
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setMemberEmail("트스트회원이메일1");
        memberSaveDTO.setMemberPassword("테스트비번1");
        memberSaveDTO.setMemberName("테스트회원이름1");

        ms.save(memberSaveDTO);


    }


    @Test
    @Transactional
    @Rollback
    @DisplayName("로그인 테스트")
    public void loginTest(){
        /*
            1.테스트용 회원가임(MemberSaveDTO)
            2.로그인용 객체 생성(MemberLoginDTO)
            1.2. 수행할 때 동일한 이메일, 패스워드를 사용하도록함.
            3.로그인 수행
            4.로그인결과가 true인지 확인
         */
        //given
        String testMemberEmail = "로그인테스트이메일";
        String testMemberPassword = "로그인테스트비밀번호";
        String testMemberName = "로그인테스트이름";
        //1.
        /*
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(testMemberEmail,testMemberPassword,testMemberName);
        ms.save(memberSaveDTO);
        //when
        //2.
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO(testMemberEmail,testMemberPassword);
        boolean loginResult = ms.login(memberLoginDTO);
        //than
        assertThat(loginResult).isEqualto(true);*/    }
}
