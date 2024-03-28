package com.mysite.sbb3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter // 보안적 측면에서 사용을 권하지 않는다.
@Entity
public class Question {

    @Id // 기본키로 설정
    // 해당 속성에 값을 일일이 입력하지 않아도 자동으로 1씩 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    // @Column을 사용하지 않아도 열로 인식
    // @Transient는 엔티티의 속성을 테이블의 열이 아닌 클래스의 속성 기능으로만 사용
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    // create_date로 데이터베이스 테이블의 열이름이 된다.
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    // cascade = CascadeType.REMOVE 질문 삭제 시 답변들까지 모두 삭제
    private List<Answer> answerList;
}
