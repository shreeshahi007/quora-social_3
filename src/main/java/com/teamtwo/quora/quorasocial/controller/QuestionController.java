package com.teamtwo.quora.quorasocial.controller;

import com.teamtwo.quora.quorasocial.Entity.FollowingList;
import com.teamtwo.quora.quorasocial.Entity.Question;
import com.teamtwo.quora.quorasocial.dto.QuestionDto;
import com.teamtwo.quora.quorasocial.services.QuestionService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value="/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    /*

    public List<QuestionDto> getTopFeed(String userId){
        //develop logic to get the list of users / categories this current user is following
        //select questions from previous list of detail you got it
        //limit the results to your page size
        //for each question load answers, comments, social information -> this you are doing through services classes of each of the objects
        return null;
    }
*/

    @GetMapping(value = "/getFeedByCategory/{categoryId}")
    public List<QuestionDto> getFeedByCategoryId(@PathVariable("categoryId")String categoryId) {
        return questionService.getQuestionDtoByCategoryId(categoryId);
    }

    @PostMapping(value="/addQuestion/{userId}/{categoryId}")
    public void addQuestion(@PathVariable("userId")String userId, @PathVariable("categoryId")String categoryId, @RequestBody String questionString)
    {
        questionService.addQuestion(userId, categoryId, questionString);
    }


/*
    @GetMapping(value="/getQuestion/{userId}/QuestionId")
    public QuestionDto getQuestion(@PathVariable("userId")String  userId,@PathVariable("questionId")int questionId)
    {*/

    @GetMapping(value="/getQuestion/{questionId}")
    public QuestionDto getQuestion(@PathVariable("questionId")String questionId)
    {
        return questionService.getQuestionDto( questionId);
    }


    @PostMapping(value="/save")
    public Question save(@RequestBody Question question){
        return questionService.save(question);
    }


    @GetMapping(value="/getFeed/{userId}")
    public List<QuestionDto> getPersonFeed(@PathVariable("userId") String userId){
        return questionService.getMainFeed(userId);
    }




}
