package com.teamtwo.quora.quorasocial.services.impl;

import com.teamtwo.quora.quorasocial.Entity.Comment;
import com.teamtwo.quora.quorasocial.dto.CommentDto;
import com.teamtwo.quora.quorasocial.repository.CommentRepository;
import com.teamtwo.quora.quorasocial.services.CommentService;
import com.teamtwo.quora.quorasocial.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SocialService socialService;


    @Override
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    @Override
    public  Comment findById(String commentId){

        return commentRepository.findById(commentId).get();

    }

    @Override
    public Comment addQuestionComment(String userId, String questionId, String commentString) {

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setQuestionId(questionId);
        comment.setCommentString(commentString);
        //commentRepository.addQuestionComment(userId,questionId,commentString);
        return commentRepository.save(comment);
    }

    @Override
    public Comment addAnswerComment(String userId, String answerId, String commentString) {

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setAnswerId(answerId);
        comment.setCommentString(commentString);

        return commentRepository.save(comment);

        //commentRepository.addAnswerComment(userId,answerId,commentString);

    }

    @Override
    public List<Comment> getQuestionComments(String questionId) {
        return commentRepository.findByQuestionId(questionId);
    }

    @Override
    //TODO check what it does
    public List<Comment> getAnswerComments(String answerId) {
        return commentRepository.findByAnswerId(answerId);
    }


     @Override
     public List<CommentDto> getQuestionCommentsSocial(String questionId){

        List<CommentDto> listComment = new ArrayList<>();

        List<Comment> list1 = commentRepository.findByQuestionId(questionId);

        for(int i= 0 ;i < list1.size();i++){

            Comment newComment = findById(list1.get(i).getCommentId());
            CommentDto commentDto= new CommentDto();

            commentDto.setCommentId(list1.get(i).getCommentId());
            commentDto.setCommentString(newComment.getCommentString());
            commentDto.setUserId(newComment.getUserId());
            commentDto.setSocialDto(socialService.getCommentSocial(list1.get(i).getCommentId()));

            listComment.add(commentDto);
        }

        return listComment ;
     }

    @Override
    public List<CommentDto> getAnswerCommentsSocial(String userId,String answerId){

        List<CommentDto> listComment = new ArrayList<>();

        List<String> list1 = commentRepository.getAnswerComments(answerId);

        for(int i= 0 ;i < list1.size();i++){

            Comment newComment = findById(list1.get(i));
            CommentDto commentDto= new CommentDto();

            commentDto.setCommentId(list1.get(i));
            commentDto.setCommentString(newComment.getCommentString());
            commentDto.setUserId(newComment.getUserId());
            commentDto.setSocialDto(socialService.getCommentSocial(list1.get(i)));
            listComment.add(commentDto);
        }

        return listComment ;
    }





}
