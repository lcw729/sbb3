package com.mysite.sbb3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러 기능을 수행한다는 의미
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Spring Boot Board";
    }
}
