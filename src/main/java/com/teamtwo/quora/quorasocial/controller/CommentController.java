package com.teamtwo.quora.quorasocial.controller;

import com.teamtwo.quora.quorasocial.Entity.Comment;
import com.teamtwo.quora.quorasocial.dto.CommentDto;
import com.teamtwo.quora.quorasocial.dto.QuestionDto;
import com.teamtwo.quora.quorasocial.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value="/comment")
public class CommentController {



    @Autowired
    CommentService commentService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value="/save")
    public  Comment save(@RequestBody Comment comment){
        return commentService.save(comment);
    }

    @PostMapping(value="/addQuestionComment/{userId}/{questionId}")
    public Comment addQuestionComment(@PathVariable("userId") String userId, @PathVariable("questionId") String questionId, @RequestBody String commentString) {
        return commentService.addQuestionComment(userId, questionId, commentString);
    }
    @GetMapping(value = "/getQuestionComments/{questionId}")
    List<CommentDto> getQuestionCommentsSocial(@PathVariable("questionId") String questionId){
        return commentService.getQuestionCommentsSocial(questionId);
    }

    @PostMapping(value="/addAnswerComment/{userId}/{answerId}")
    public void addAnswerComment(@PathVariable("userId") String  userId,@PathVariable("answerId") String answerId,@RequestBody String commentString) {
        commentService.addAnswerComment(userId, answerId, commentString);
    }

//   @GetMapping(value="/getQuestionComments/{questionId}")
//   {
//      getQuestionComments/{questionId} public CommentDto getQuestionComments(@PathVariable("questionId")int questionId)
//       {
//           return commentService.getCommentDto( questionId);
//       }
//   }

}
