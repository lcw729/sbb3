package com.mysite.sbb3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Sbb3ApplicationTests {

    // @Autowired 애너테이션을 해당 변수에 적용하면, 스프링 부트가 객체를 자동으로 주입
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        // SELECT * FROM QUESTION
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q0 = all.get(0);
        assertEquals("sbb가 무엇인가요?", q0.getSubject());

        /*
        // Optional 리턴 타입 => findById로 호출한 값이 존재할 수도 있고, 존재하지 않을 수도 있다.
        Optional<Question> oq = this.questionRepository.findById(1);
        // 값이 존재하는지 체크한다.
        if(oq.isPresent()) {
            Question q1 = oq.get();
            assertEquals("sbb가 무엇인가요?", q1.getSubject());
        }
         */

        Question q3 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q3.getId());

        Question q4 = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q4.getId());

        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q5 = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q5.getSubject());
    }

}
