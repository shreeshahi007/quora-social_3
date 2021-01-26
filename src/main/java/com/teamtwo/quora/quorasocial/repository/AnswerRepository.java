package com.teamtwo.quora.quorasocial.repository;

import com.teamtwo.quora.quorasocial.Entity.Answer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends CrudRepository<Answer,String>
{

    /*@Modifying
    @Query(value="insert into Answer(questionId,userId,answerString) VALUES (:questionId,:userId,:answerString)",nativeQuery = true)
    void addAnswer(@Param("questionId")int questionId,@Param("userId")String userId,@Param("answerString")String answerString);
*/

    @Modifying
    @Query("select A.id from Answer A where A.questionId = :questionId")
    List<String> getAnswersQuestion(@Param("questionId")String questionId);

    List<Answer> findByQuestionId(String questionId);

    List<Answer> findByUserId(String str);
}
