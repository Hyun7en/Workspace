package com.kh.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("/board")
public class BootController {

//    @GetMapping("/dummy")
//    public String dummy(){
//      log.info("dummy성공");
//      return "ok";
//    }
//
//    @GetMapping("/member")
//    public String getMember(@RequestParam(value = "memberKey", defaultValue = "111") String memberKey,
//                            @RequestParam(value = "age") int age){
//        log.info("memberKey : {}", memberKey);
//        log.info("age : {}", age);
//        return "ok";
//    }

    //ResponseEntity : Http응답을 나타내는 클래스이다.
    @PostMapping
    public ResponseEntity<Boolean> create(){

        //Board객체를 생성하기위함
        // userId, pwd, title, contents


        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping
    public void getBoardList(){

    }
}
