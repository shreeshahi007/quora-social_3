package com.teamtwo.quora.quorasocial.services;

import com.teamtwo.quora.quorasocial.Entity.Answer;
import com.teamtwo.quora.quorasocial.dto.AnswerDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerService {


    void addAnswer(String questionId,String userId,String answerString);
    Answer findById(String questionId);
    Answer save(Answer answer);
    List<AnswerDto> getAnswerDto(String userId, String questionId);

    List<Answer> getQuestionAnswers(String questionId);

    List<Answer> getAnswerByUserId(String str);
}
