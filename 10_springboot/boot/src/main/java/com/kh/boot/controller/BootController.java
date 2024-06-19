package com.kh.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BootController {

    @GetMapping("/dummy")
    public String dummy(){
      log.info("dummy성공");
      return "ok";
    }

    @GetMapping("/member")
    public String getMember(@RequestParam(value = "memberKey", defaultValue = "111") String memberKey,
                            @RequestParam(value = "age") int age){
        log.info("memberKey : {}", memberKey);
        log.info("age : {}", age);
        return "ok";
    }
}
