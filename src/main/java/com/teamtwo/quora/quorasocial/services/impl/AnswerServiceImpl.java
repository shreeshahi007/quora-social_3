package com.teamtwo.quora.quorasocial.services.impl;

import com.teamtwo.quora.quorasocial.Entity.Answer;
import com.teamtwo.quora.quorasocial.dto.AnswerDto;
import com.teamtwo.quora.quorasocial.repository.AnswerRepository;
import com.teamtwo.quora.quorasocial.services.AnswerService;
import com.teamtwo.quora.quorasocial.services.CommentService;
import com.teamtwo.quora.quorasocial.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    SocialService socialService;

    @Autowired
    CommentService commentService;


    @Override
    public Answer save(Answer answer){
        return  answerRepository.save(answer);
    }

    @Override
    public Answer findById(String questionId){
        return answerRepository.findById(questionId).get();
    }

    @Override
    public void addAnswer(String questionId, String userId, String answerString) {
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserId(userId);
        answer.setAnswerString(answerString);
        //TODO added time stamp
        answer.setTimeStamp(new Date().toString());
        answerRepository.save(answer);
        //answerRepository.addAnswer(questionId, userId, answerString);
    }


    //public List<AnswerDto> getAnswerDto(String  userId,int questionId){
    //
    //

    @Override
    //TODO the userId is useless so im making a new function
    public List<AnswerDto> getAnswerDto(String  userId,String questionId){

        List<AnswerDto> listAnswer = new ArrayList<>();

        List<String> list1= answerRepository.getAnswersQuestion(questionId);

        for(int i=0;i < list1.size();i++){
            Answer newAnswer = findById(list1.get(i));

            AnswerDto answerDto = new AnswerDto();

            answerDto.setAnswerId(newAnswer.getAnswerId());
            answerDto.setAnswerString(newAnswer.getAnswerString());
            answerDto.setUserId(newAnswer.getUserId());
            answerDto.setSocialDto(socialService.getAnswerSocial(list1.get(i)));
            answerDto.setCommentDtoList(commentService.getAnswerCommentsSocial(newAnswer.getUserId(),newAnswer.getAnswerId()));

            listAnswer.add(answerDto);
        }

        return listAnswer;
    }
    //TODO the new function
    public List<AnswerDto> getAnswerDto(String questionId){

        List<AnswerDto> listAnswer = new ArrayList<>();

        List<String> list1= answerRepository.getAnswersQuestion(questionId);

        for(int i=0;i < list1.size();i++){
            Answer newAnswer = findById(list1.get(i));

            AnswerDto answerDto = new AnswerDto();

            answerDto.setAnswerId(newAnswer.getAnswerId());
            answerDto.setAnswerString(newAnswer.getAnswerString());
            answerDto.setUserId(newAnswer.getUserId());
            answerDto.setSocialDto(socialService.getAnswerSocial(list1.get(i)));
            answerDto.setCommentDtoList(commentService.getAnswerCommentsSocial(newAnswer.getUserId(),newAnswer.getAnswerId()));

            listAnswer.add(answerDto);
        }

        return listAnswer;
    }

    @Override
    public List<Answer> getQuestionAnswers(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> getAnswerByUserId(String str) {
        return answerRepository.findByUserId(str);
    }
}
