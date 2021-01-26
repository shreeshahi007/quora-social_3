package com.teamtwo.quora.quorasocial.Entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name="comm")
public class Comment {

    @Id
    @GeneratedValue(generator = "seq_gen_alias") @GenericGenerator(name="seq_gen_alias",strategy = "uuid2")
    private String commentId;
    private String questionId;
    private String answerId;
    private String userId;
    private String commentString;
    private String Timestamp = String.valueOf(LocalTime.now());

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}
