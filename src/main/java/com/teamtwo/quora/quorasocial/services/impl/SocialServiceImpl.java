package com.teamtwo.quora.quorasocial.services.impl;

import com.teamtwo.quora.quorasocial.Entity.Social;
import com.teamtwo.quora.quorasocial.dto.SocialDto;
import com.teamtwo.quora.quorasocial.repository.SocialRepository;
import com.teamtwo.quora.quorasocial.sample.CountStruct;
import com.teamtwo.quora.quorasocial.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SocialServiceImpl implements SocialService {


    @Autowired
    SocialRepository socialRepository;

    @Override
    public Social save(Social social) {
        return socialRepository.save(social);
    }

    @Override
    public void addQuestionReaction(String userId, String questionId, String reactionType, int reactionValue) {

        Social social= new Social();
        social.setUserId(userId);
        social.setQuestionId(questionId);
        social.setReactionType(reactionType);
        social.setReactionValue(reactionValue);

        socialRepository.save(social);

        //socialRepository.addQuestionReaction(userId,questionId,reactionType,reactionValue);
    }

    @Override
    public void deleteQuestionReaction(String userId, String questionId) {

        socialRepository.deleteQuestionReaction(userId,questionId);
    }

    @Override
    public void changeQuestionReaction(String userId, String questionId, String reactionType, int reactionValue) {

        socialRepository.changeQuestionReaction(userId,questionId,reactionType,reactionValue);
    }

    @Override
    public void addAnswerReaction(String userId, String answerId, String reactionType, int reactionValue) {

        Social social = new Social();
        social.setUserId(userId);
        social.setAnswerId(answerId);
        social.setReactionType(reactionType);
        social.setReactionValue(reactionValue);

        socialRepository.save(social);

        //socialRepository.addAnswerReaction(userId,answerId,reactionType,reactionValue);
    }

    @Override
    public void deleteAnswerReaction(String userId, String answerId) {
        socialRepository.deleteAnswerReaction(userId,answerId);
    }

    @Override
    public void changeAnswerReaction(String userId, String answerId, String reactionType, int reactionValue) {
       socialRepository.changeAnswerReaction(userId,answerId,reactionType,reactionValue);
    }

    @Override
    public void addCommentReaction(String userId, String commentId, String reactionType, int reactionValue) {

        Social social = new Social();
        social.setUserId(userId);
        social.setAnswerId(commentId);
        social.setReactionType(reactionType);
        social.setReactionValue(reactionValue);

        socialRepository.save(social);

        //socialRepository.addCommentReaction(userId,commentId,reactionType,reactionValue);
    }

    @Override
    public void deleteCommentReaction(String userId, String commentId) {
        socialRepository.deleteCommentReaction(userId,commentId);
    }

    @Override
    public void changeCommentReaction(String userId, String commentId, String ReactionType, int reactionValue) {
       socialRepository.changeCommentReaction(userId,commentId,ReactionType,reactionValue);
    }

    @Override
    public List<CountStruct> getReactionCountQuestion(String questionId) {
        List<Object[]> results = socialRepository.getReactionCountQuestion(questionId);
        List<CountStruct> countStructList = new ArrayList<>();
        for(Object[] result :results){
            CountStruct countStruct = new CountStruct();
            countStruct.setCount((int)(long) result[0]);
            countStruct.setReactionType((String)result[1]);

            countStructList.add(countStruct);
        }
        return countStructList;
        //return socialRepository.getReactionCountQuestion(questionId);
//        Map<Integer, String> response = socialRepository.getReactionCountQuestion(questionId);
//        List<CountStruct> countStructs = new ArrayList<>();
//        for(Map.Entry<Integer, String> entry : response.entrySet()) {
//            CountStruct countStruct = new CountStruct();
//            countStruct.setCount(entry.getKey());
//            countStruct.setReactionType(entry.getValue());
//            countStructs.add(countStruct);
//        }
//        return countStructs;
    }

    @Override
    public List<CountStruct> getReactionCountAnswer(String answerId) {
        List<Object[]> results = socialRepository.getReactionCountAnswer(answerId);
        List<CountStruct> countStructList = new ArrayList<>();
        for(Object[] result :results){
            CountStruct countStruct = new CountStruct();
            countStruct.setCount((int) (long) result[0]);
            countStruct.setReactionType((String)result[1]);

            countStructList.add(countStruct);
        }
        return countStructList;
        // return socialRepository.getReactionCountAnswer(answerId);

//        Map<Integer, String> response = socialRepository.getReactionCountAnswer(answerId);
//        List<CountStruct> countStructs = new ArrayList<>();
//        for(Map.Entry<Integer, String> entry : response.entrySet()) {
//            CountStruct countStruct = new CountStruct();
//            countStruct.setCount(entry.getKey());
//            countStruct.setReactionType(entry.getValue());
//            countStructs.add(countStruct);
//        }
//        return countStructs;
    }

    @Override
    public List<CountStruct> getReactionCountComment(String commentId) {

        List<Object[]> results = socialRepository.getReactionCountComment(commentId);
        List<CountStruct> countStructList = new ArrayList<>();
        for(Object[] result :results){
            CountStruct countStruct = new CountStruct();
            countStruct.setCount((int)(long) result[0]);
            countStruct.setReactionType((String)result[1]);

            countStructList.add(countStruct);
        }
        return countStructList;







        //return socialRepository.getReactionCountComment(commentId);
//        Map<Integer, String> response = socialRepository.getReactionCountComment(commentId);
//        List<CountStruct> countStructs = new ArrayList<>();
//        for(Map.Entry<Integer, String> entry : response.entrySet()) {
//            CountStruct countStruct = new CountStruct();
//            countStruct.setCount(entry.getKey());
//            countStruct.setReactionType(entry.getValue());
//            countStructs.add(countStruct);
//        }
//        return countStructs;
    }

    @Override
    public SocialDto getQuestionSocial(String questionId){
        SocialDto socialdto = new SocialDto();
        socialdto.setQuestionId(questionId);
        List<CountStruct> list = getReactionCountQuestion(questionId);

        for(int i=0;i<list.size();i++){
            if(list.get(i).getReactionType().equals("upvote")){
                socialdto.setCountUp(list.get(i).getCount());
            }
            else if( list.get(i).getReactionType().equals("downvote"))
            {
                socialdto.setCountDown(list.get(i).getCount());
            }
            else{
                socialdto.setCountBulb(list.get(i).getCount());
            }
        }

        return socialdto;

    }

    @Override
    public SocialDto getAnswerSocial(String answerId){
        SocialDto socialdto = new SocialDto();
        socialdto.setAnswerId(answerId);
        List<CountStruct> list = getReactionCountAnswer(answerId);

        for(int i=0;i<list.size();i++){
            if(list.get(i).getReactionType().equals("upvote")){
                socialdto.setCountUp(list.get(i).getCount());
            }
            else if( list.get(i).getReactionType().equals("downvote"))
            {
                socialdto.setCountDown(list.get(i).getCount());
            }
            else{
                socialdto.setCountBulb(list.get(i).getCount());
            }
        }

        return socialdto;

    }

    @Override
    public SocialDto getCommentSocial(String commentId){
        SocialDto socialdto = new SocialDto();
        socialdto.setCommentId(commentId);
        List<CountStruct> list = getReactionCountComment(commentId);

        for(int i=0;i<list.size();i++){
            if(list.get(i).getReactionType().equals("upvote")){
                socialdto.setCountUp(list.get(i).getCount());
            }
            else if( list.get(i).getReactionType().equals("downvote"))
            {
                socialdto.setCountDown(list.get(i).getCount());
            }
            else{
                socialdto.setCountBulb(list.get(i).getCount());
            }
        }

        return socialdto;

    }



}
