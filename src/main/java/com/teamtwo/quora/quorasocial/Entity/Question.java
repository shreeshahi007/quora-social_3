package com.teamtwo.quora.quorasocial.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="ques")
public class Question {
    @Id
    @GeneratedValue(generator = "seq_gen_alias") @GenericGenerator(name="seq_gen_alias",strategy = "uuid2")
    private String questionId;
    private String userId;
    private String categoryId;
    private String questionString;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }
}
