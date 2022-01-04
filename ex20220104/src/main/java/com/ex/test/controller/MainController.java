package com.ex.test.controller;

// 기본주소가 요청됐을 때 index를 출력하는 메서드 정의

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    /*
        Rest Api


        GetMapping
        PostMapping
        PutMapping
        DeleteMapping

    */

    @GetMapping("/")
    public String index(){
        System.out.println("index 호출");

        return "index";
    }
}
