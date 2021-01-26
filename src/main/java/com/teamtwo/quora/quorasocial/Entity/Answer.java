package com.teamtwo.quora.quorasocial.Entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.time.LocalTime;


@Entity
@Table(name="ans")
public class Answer {

    @Id
    @GeneratedValue(generator = "seq_gen_alias") @GenericGenerator(name="seq_gen_alias",strategy = "uuid2")
    private String answerId;
    private String questionId;
    private String userId;
    private String answerString;
    private String TimeStamp = String.valueOf(LocalTime.now());

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

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

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
