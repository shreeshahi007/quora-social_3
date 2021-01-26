package com.teamtwo.quora.quorasocial.repository;

import com.teamtwo.quora.quorasocial.Entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment,String> {



/*

    @Modifying
    @Query(value="insert into Comment(userId,questionId,commentString) VALUES (:userId,:questionId,:commentString)",nativeQuery = true)
    void addQuestionComment(@Param("userId")String userId,@Param("questionId")int questionId,@Param("commentString")String commentString);

    @Modifying
    @Query(value="insert into Comment(userId,answerId,commentString) VALUES (:userId,:answerId,:commentString)",nativeQuery = true)
    void addAnswerComment(@Param("userId")String userId,@Param("answerId")int answerId,@Param("commentString")String commentString);

*/

    @Modifying
    //TODO check the custom queries
    @Query("select id from Comment where questionId = :questionId")
    List<String> getQuestionComments(@Param("questionId")String questionId);

    @Modifying
    @Query("select id from Comment where answerId = :answerId")
    List<String> getAnswerComments(@Param("answerId")String answerId);


    List<Comment> findByQuestionId(String questionId);

    List<Comment> findByAnswerId(String answerId);
}
