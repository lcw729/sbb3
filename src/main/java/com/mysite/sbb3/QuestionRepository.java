package com.mysite.sbb3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Question 엔티티로 리포지터리 생성
// Question 엔티티의 기본키가 Integer임
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // findBy + 엔티티의 속성명
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);

    // 특정 문자열을 포함하는 데이터 찾기
    List<Question> findBySubjectLike(String subject);
}
