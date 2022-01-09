package com.icia.member;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;


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
    @Transactional //테스트 시작할때 새로운 트랜잭션 시작
    @Rollback   //테스트 종료 후 롤백 수행
    @DisplayName("회원조회 테스트")
    public void memberDetailTest(){
        // given: 테스트조건설정
        // 1.새로운 회원을 등록하고 해당회원의 번호(member_id)를 가져옴.
        //테스트용 데이터 객체를 생성
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO("조회용회원이메일1","조회용회원비번1","조회용회원이름1");
        //테스트용 데이터를 DB에 저장하고 member_id를 가져옴
        Long memberId =ms.save(memberSaveDTO);
        // when: 테스트수행
        // 2.위에서 가져온 회원번호를 가지고 조회기능 수행
        MemberDetailDTO findMember = ms.findById(memberId);
        //then: 테스트결과 검증
        // 3.1번에서 가입한 회원의 정보와 2번에서 조회한 회원의 정보가 일치하면 테스트 통과 일치하지 않으면 테스트 실패
        // memberSaveDTO의 이메일값과 findMember의 이메일 갑이 일치하는지 확인.
        assertThat(memberSaveDTO.getMemberEmail()).isEqualTo(findMember.getMemberEmail());

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
