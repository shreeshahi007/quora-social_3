package com.teamtwo.quora.quorasocial.services.impl;

import com.teamtwo.quora.quorasocial.Entity.Answer;
import com.teamtwo.quora.quorasocial.Entity.Comment;
import com.teamtwo.quora.quorasocial.Entity.FollowingList;
import com.teamtwo.quora.quorasocial.Entity.Question;
import com.teamtwo.quora.quorasocial.dto.AnswerDto;
import com.teamtwo.quora.quorasocial.dto.CommentDto;
import com.teamtwo.quora.quorasocial.dto.QuestionDto;
import com.teamtwo.quora.quorasocial.repository.QuestionRepository;
import com.teamtwo.quora.quorasocial.services.AnswerService;
import com.teamtwo.quora.quorasocial.services.CommentService;
import com.teamtwo.quora.quorasocial.services.QuestionService;
import com.teamtwo.quora.quorasocial.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerService answerService;

    @Autowired
    CommentService commentService;

    @Autowired
    SocialService socialService;

    @Override
    public void addQuestion(String userId, String categoryId, String questionString) {
        Question question = new Question();

         question.setUserId(userId);
         question.setCategoryId(categoryId);
         question.setQuestionString(questionString);
       //questionRepository.addQuestion(userId, categoryId, questionString);
        questionRepository.save(question);
    }

    @Override
    public Question findById(String questionId){
        return questionRepository.findById(questionId).get();
    }


    //public QuestionDto getQuestionDto(String userId,int questionId){
    @Override
    public QuestionDto getQuestionDto(String questionId){

        Question newQuestion = findById(questionId);

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionString(newQuestion.getQuestionString());
        questionDto.setQuestionId(newQuestion.getQuestionId());
        questionDto.setCategoryId(newQuestion.getCategoryId());
        questionDto.setUserId(newQuestion.getUserId());
        questionDto.setAnswerDtoList(answerService.getAnswerDto(newQuestion.getUserId(), questionId));
        questionDto.setCommentDtoList(commentService.getQuestionCommentsSocial(questionId));
        questionDto.setSocialDto(socialService.getQuestionSocial(questionId));

        return questionDto;
    }

    @Override
    public List<QuestionDto> getQuestionDtoByCategoryId(String categoryId) {
        List<Question> questionsList = questionRepository.findByCategoryId(categoryId);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : questionsList) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestionId(question.getQuestionId());
            questionDto.setQuestionString(question.getQuestionString());
            questionDto.setCategoryId(question.getCategoryId());
            questionDto.setUserId(question.getUserId());
            questionDto.setSocialDto(socialService.getQuestionSocial(question.getQuestionId()));


            List<Comment> questionComments= commentService.getQuestionComments(question.getQuestionId());
            List<CommentDto> questionCommentDtoList = new ArrayList<>();
            for(Comment comment: questionComments) {
                CommentDto commentDto=new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setCommentString(comment.getCommentString());
                commentDto.setUserId(comment.getUserId());
                commentDto.setSocialDto(socialService.getCommentSocial(comment.getCommentId()));
                questionCommentDtoList.add(commentDto);
            }
            questionDto.setCommentDtoList(questionCommentDtoList);


            Answer answer= answerService.getQuestionAnswers(question.getQuestionId()).get(0);
            List<AnswerDto> answerDtoList=new ArrayList<>();
            AnswerDto answerDto=new AnswerDto();
            answerDto.setAnswerId(answer.getAnswerId());
            answerDto.setAnswerString(answer.getAnswerString());
            answerDto.setUserId(answer.getUserId());
            answerDto.setSocialDto(socialService.getAnswerSocial(answer.getAnswerId()));
            List<Comment> answerComments=commentService.getAnswerComments(answer.getAnswerId());
            List<CommentDto> answerCommentDtoList = new ArrayList<>();
            for(Comment comment : answerComments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setCommentString(comment.getCommentString());
                commentDto.setUserId(comment.getUserId());
                commentDto.setSocialDto(socialService.getCommentSocial(comment.getCommentId()));
                answerCommentDtoList.add(commentDto);
            }
            answerDto.setCommentDtoList(answerCommentDtoList);
            questionDto.setAnswerDtoList(answerDtoList);



//            List<CommentDto> commentDtoList1=new ArrayList<>();
//            for(Answer answer:answers){
//
//            }
//            questionDto.setCommentDtoList(commentDtoList);
//            Answer answer = answerService.getQuestionAnswers(question.getQuestionId()).get(0);
//            List<AnswerDto> answerDtoList = new ArrayList<>();
//            answerDtoList.add(socialService.getAnswerSocial(answer.getAnswerId()))
        }
        return questionDtoList;
    }

    @Override
    public List<QuestionDto> getMainFeed(String userId) {
        FollowingList followingList = restTemplate.getForObject("http://localhost:9007/profile/getFollowersList/"+userId, FollowingList.class);
        List<String> followingUserIds = followingList.getFollowersList();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(String str: followingUserIds){
            List<Question> questionList = questionRepository.findByUserId(str);
            for(Question question : questionList) {
                QuestionDto questionDto = getQuestionDto(question.getQuestionId());
                questionDtoList.add(questionDto);
            }
            List<Answer> answerList = answerService.getAnswerByUserId(str);
            for(Answer answer : answerList) {
                QuestionDto questionDto = getQuestionDto(answer.getQuestionId());
                if(!questionDtoList.contains(questionDto)) {
                    questionDtoList.add(questionDto);
                }
            }
        }
        return questionDtoList;
    }

    @Override
    public Question save(Question question){

       return  questionRepository.save(question);
    }
}
