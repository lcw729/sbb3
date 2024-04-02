package com.mysite.sbb3.question;

import com.mysite.sbb3.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> q = this.questionRepository.findById(id);
        if(q.isPresent()) {
            return q.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}
