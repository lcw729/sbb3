package com.mysite.sbb3;

import com.mysite.sbb3.answer.Answer;
import com.mysite.sbb3.answer.AnswerRepository;
import com.mysite.sbb3.question.Question;
import com.mysite.sbb3.question.QuestionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest // 테스트 환경을 기본설정해주는 어노테이션
class Sbb3ApplicationTests {

    // @Autowired 애너테이션을 해당 변수에 적용하면, 스프링 부트가 객체를 자동으로 주입
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testQuestion() {

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

        // 질문 데이 수정하기
        Optional<Question> oq = this.questionRepository.findById(1);
        // 괄호 안의 값이 true인지 테스트한다.
        assertTrue(oq.isPresent());

        Question q6 = oq.get();
        q6.setSubject("수정된 제목");
        this.questionRepository.save(q6);

        // 질문 데이터 삭제하기
        /*
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq2 = this.questionRepository.findById(1);
        assertTrue(oq2.isPresent());
        Question q7 = oq2.get();
        this.questionRepository.delete(q7);
        assertEquals(1, this.questionRepository.count());
         */
    }

    @Transactional
    @Test
    void TestAnswer() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q); // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);

        // 답변 데이터 조회하기
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a2 = oa.get();
        assertEquals(2, a2.getQuestion().getId());

        // 질문 데이터를 통해 답변 데이터 찾기
        /*
        Question을 조회하고 나면 DB 세션이 끊어진다.
        - answerList는 q 객체를 조회할 때가 아니라 q.getAnswerList() 메서드 호출 시점에 가져온다. 에러 발생!
        => Lazy 방식 => @Transactional 어노테이션 사용하기
         */
        Optional<Question> oq2 = this.questionRepository.findById(2);
        assertTrue(oq2.isPresent());
        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());


    }
}
