package com.teamtwo.quora.quorasocial.services;

import com.teamtwo.quora.quorasocial.Entity.Question;
import com.teamtwo.quora.quorasocial.dto.QuestionDto;

import java.util.List;

public interface QuestionService {


    void addQuestion(String userId,String categoryId, String questionString);

    Question findById(String questionId);

    List<QuestionDto> getMainFeed(String userId);

    Question save(Question question);

    QuestionDto getQuestionDto( String questionId);

    List<QuestionDto> getQuestionDtoByCategoryId(String categoryId);
}
