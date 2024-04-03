package com.mysite.sbb3.question;

import com.mysite.sbb3.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    // @RequiredArgsConstructor 애너테이션의 생성자 방식으로 객체 주입
    // final이 붙은 속성을 포함하는 생성자를 자동으로 만들어준다.
    // private final QuestionRepository questionRepository;
    private final QuestionService questionService;


    // @ResponseBody : 템플릿을 사용하므로 @ResponseBody 애너테이션은 필요없음
    /*
     Model 객체 : 자바 클래스와 템플릿 간의 연결 고리 역할
     Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.
     컨트롤러 메서드에서 매개변수 지정하면 스프링 부터가 자동으로 Model 객체 생성한다.
     */
    @GetMapping("/list")
    public String list(Model model) {
        // List<Question> questionList = this.questionRepository.findAll();
        List<Question>  questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // 숫자처럼 변하는 id값을 얻을 때에는 @PathVariable 애너테이션을 사용한다.
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Integer id,
                         AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
