package com.teamtwo.quora.quorasocial.services;


import com.teamtwo.quora.quorasocial.Entity.Comment;
import com.teamtwo.quora.quorasocial.dto.CommentDto;

import java.util.List;

public interface CommentService {


    Comment addQuestionComment(String userId, String questionId, String commentString);
    Comment addAnswerComment(String userId,String answerId,String commentString);
    List<Comment> getQuestionComments(String questionId);

    //TODO check what the fuck this method does
    List<Comment> getAnswerComments(String answerId);
    Comment findById(String commentId);

    Comment save(Comment comment);

    List<CommentDto> getQuestionCommentsSocial(String questionId);
    List<CommentDto> getAnswerCommentsSocial(String userId,String questionId);

}
