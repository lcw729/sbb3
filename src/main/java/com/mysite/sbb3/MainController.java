package com.mysite.sbb3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    // @ResponseBody 생략 시, index라는 이름의 템플릿 파일을 찾는다.
    public String index(){
        return "안녕하세요. sbb에 오신 것을 환영합니다.";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
