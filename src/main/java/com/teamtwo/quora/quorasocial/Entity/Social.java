package com.teamtwo.quora.quorasocial.Entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="social")
public class Social {

    @Id
    @GeneratedValue(generator = "seq_gen_alias")
    @GenericGenerator(name = "seq_gen_alias", strategy = "uuid2")
    private String socialId;
    private String userId;
    private String questionId;
    private String answerId;
    private String commentId;
    private String reactionType; //upvote, downvote and BULB
    private int reactionValue;
//    private int upvote;
//    private int downvote;
//    private int bulb;


    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public int getReactionValue() {
        return reactionValue;
    }

    public void setReactionValue(int reactionValue) {
        this.reactionValue = reactionValue;
    }
//
//    public int getUpvote() {
//        return upvote;
//    }
//
//    public void setUpvote(int upvote) {
//        this.upvote = upvote;
//    }
//
//    public int getDownvote() {
//        return downvote;
//    }
//
//    public void setDownvote(int downvote) {
//        this.downvote = downvote;
//    }
//
//    public int getBulb() {
//        return bulb;
//    }
//
//    public void setBulb(int bulb) {
//        this.bulb = bulb;
//    }
}