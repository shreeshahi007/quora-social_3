package com.teamtwo.quora.quorasocial.controller;

import com.teamtwo.quora.quorasocial.Entity.Answer;
import com.teamtwo.quora.quorasocial.Entity.Question;
import com.teamtwo.quora.quorasocial.dto.AnswerDto;
import com.teamtwo.quora.quorasocial.dto.QuestionDto;
import com.teamtwo.quora.quorasocial.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value="/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value="/addAnswer/{questionId}/{userId}")
    public void addAnswer(@PathVariable("questionId")String questionId,@PathVariable("userId")String userId, @RequestBody String answerString){
         answerService.addAnswer(questionId, userId, answerString);
    }

     @PostMapping(value="/save")
     public Answer save(Answer answer){

        return answerService.save(answer);

     }


}
