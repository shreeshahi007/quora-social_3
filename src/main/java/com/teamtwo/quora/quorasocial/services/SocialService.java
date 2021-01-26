package com.teamtwo.quora.quorasocial.services;

import com.teamtwo.quora.quorasocial.Entity.Social;
import com.teamtwo.quora.quorasocial.dto.SocialDto;
import com.teamtwo.quora.quorasocial.sample.CountStruct;

import java.util.List;

public interface SocialService {


    Social save(Social social);
    void addQuestionReaction(String userId,String questionId,String reactionType,int reactionValue);
    void deleteQuestionReaction(String userId,String questionId);
    void changeQuestionReaction(String userId,String questionId,String ReactionType,int reactionValue);
    void addAnswerReaction(String userId,String answerId,String reactionType,int reactionValue);
    void deleteAnswerReaction(String userId,String answerId);
    void changeAnswerReaction(String userId,String answerId,String ReactionType,int reactionValue);
    void addCommentReaction(String userId,String commentId,String reactionType,int reactionValue);
    void deleteCommentReaction(String userId,String commentId);
    void changeCommentReaction(String userId,String commentId,String ReactionType,int reactionValue);

    List<CountStruct> getReactionCountQuestion(String questionId);
    List<CountStruct> getReactionCountAnswer(String answerId);
    List<CountStruct> getReactionCountComment(String commentId);

    SocialDto getQuestionSocial(String questionId);
    SocialDto getAnswerSocial(String answerId);
    SocialDto getCommentSocial(String commentId);


   /* void addBulbReaction(String userId,String answewrString reactionType,String aId);
    void deleteBulbReaction(String userId,String aId);
    void changeBulbReaction(String userId,String newReactionType,String aId);*/

}
