package com.icia.member.interceptor;

import com.icia.member.common.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {

    //Controller로 가기전에 체크한다.
    @Override//메서드를 재정리  부모를가진변수를 커스터마이징
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //Override 이기때문에 인자들을 쓰든안쓰든 다 가져와야한다.
        //클라에서 서벌로 리퀘스트     서버에서 클라로 리스폰스

        //사용자가 요청한 주소
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = "+ requestURI);
        HttpSession session = request.getSession();
        // 세션에 로그인 정보가있는지 확인
        if(session.getAttribute(SessionConst.LOGIN_EMAIL) == null){
            //미로그인 상태
            //로그인을 하지 않은 경우바로 로그인페이지로 보냄. 로그인을 하면 요청한 화면으 ㄹ 보여줌.
            response.sendRedirect("/member/login?redirectURL="+requestURI);

            return false;
        }else{
            //로그인상태
            return true;
        }
    }


}
