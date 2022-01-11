package com.icia.member;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService ms;

    @Test
    @DisplayName("회원데이터생성")
    public void AnewMembers(){
        IntStream.rangeClosed(1,15).forEach(i ->{
            ms.save(new MemberSaveDTO("email"+i, "pw"+i, "name"+i));
        });
    }
    /*
            회원 삭제 테스트코드를 만들어봅시다.
            회원삭제 시나리오를 작성해보고 코드도 짜보도록 11시 15분에 같이함.
    */
    // 회원 등록하고  삭제하고  삭제된지확인까지가 테스트
    @Test
    @Transactional
    @Rollback
    @DisplayName("회원삭제")
    public void memberDeleteTest() {

        MemberSaveDTO memberSaveDTO = new MemberSaveDTO("삭제용회원1","삭제용비밀번호1","삭제용이름1");
        Long memberId=ms.save(memberSaveDTO);
        System.out.println(memberId);

        ms.deleteById(memberId);


        //System.out.println(ms.findById(memberId));  <error
        //삭제한 회원의 id로 조회를 시도햇을 떄 null이여야 테스트통과
        //NoSuchElementException은 무시하고 테스트를 수행
        assertThrows(NoSuchElementException.class, ()->{
            assertThat(ms.findById(memberId)).isNull();//삭제 회원 id 조회결과가 null 이면 테스트통과
        });

    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("updatetest")
    public void Test_update(){

        MemberSaveDTO memberSaveDTO = new MemberSaveDTO("이메일1","패스워드1","이름1");
        Long memberId=ms.save(memberSaveDTO);
        String memberName =memberSaveDTO.getMemberName();
        String updateName = "update";


        MemberDetailDTO memberDetailDTO=ms.findById(memberId);
        memberDetailDTO.setMemberName(updateName);
        memberId= ms.update(memberDetailDTO);

        assertThat(updateName).isEqualTo(memberDetailDTO.getMemberName());
        //isBotEqualTo 달라야통과
        //assertThat(updateName).isNotEqualTo(memberDetailDTO.getMemberName());


        /*
        1.신규회원등록
        2.신규등록한 회원에 대한 이름 수정
        3. 신규등록시 사용한 이름과 DB에 저장된 이름이 일치하는지판단
        4.일치하지않아야 테스트통과
         */

    }
}
