package com.mysite.sbb3.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class QuestionController {

    // @RequiredArgsConstructor 애너테이션의 생성자 방식으로 객체 주입
    // final이 붙은 속성을 포함하는 생성자를 자동으로 만들어준다.
    private final QuestionRepository questionRepository;


    // @ResponseBody : 템플릿을 사용하므로 @ResponseBody 애너테이션은 필요없음
    /*
     Model 객체 : 자바 클래스와 템플릿 간의 연결 고리 역할
     Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.
     컨트롤러 메서드에서 매개변수 지정하면 스프링 부터가 자동으로 Model 객체 생성한다.
     */
    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}