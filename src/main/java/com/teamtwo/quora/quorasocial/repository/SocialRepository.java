package com.teamtwo.quora.quorasocial.repository;

import com.teamtwo.quora.quorasocial.sample.CountStruct;
import com.teamtwo.quora.quorasocial.Entity.Social;
import com.teamtwo.quora.quorasocial.sample.CountStruct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;


@Repository
public interface SocialRepository extends CrudRepository<Social,String> {


   /* @Modifying
    @Query(value="insert into Social t (t.userId,t.questionId,t.reactionType,t.reactionValue) VALUES (:userId,:questionId,:reactionType,:reactionValue)",nativeQuery = true)
    void addQuestionReaction(@Param("userId")String userId,@Param("questionId")int questionId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue );
*/

    @Modifying
    @Query("update Social t set t.reactionType ='',t.reactionValue = 0 where t.userId = :userId and t.questionId = :questionId ")
    void deleteQuestionReaction(@Param("userId")String userId,@Param("questionId")String questionId);


    @Modifying
    @Query("update Social t set t.reactionType= :reactionType, t.reactionValue = :reactionValue where t.userId = :userId and t.questionId = :questionId")
    void changeQuestionReaction(@Param("userId")String userId,@Param("questionId")String questionId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue);

   /* @Modifying
    @Query(value="insert into Social t (t.userId,t.answerId,t.reactionType,t.reactionValue) VALUES (:userId,:answerId,:reactionType,:reactionValue)",nativeQuery = true)
    void addAnswerReaction(@Param("userId")String userId,@Param("answerId")int answerId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue );
*/

    @Modifying
    @Query("update Social t set t.reactionType ='', t.reactionValue = 0 where t.userId = :userId and t.answerId = :answerId ")
    void deleteAnswerReaction(@Param("userId")String userId,@Param("answerId")String answerId);


    @Modifying
    @Query("update Social t set t.reactionType= :reactionType, t.reactionValue = :reactionValue where t.userId = :userId and t.answerId = :answerId")
    void changeAnswerReaction(@Param("userId")String userId,@Param("answerId")String answerId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue);


   /* @Modifying
    @Query(value="insert into Social t (t.userId,t.commentId,t.reactionType,t.reactionValue) VALUES (:userId,:commentId,:reactionType,:reactionValue)",nativeQuery = true)
    void addCommentReaction(@Param("userId")String userId,@Param("commentId")int commentId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue );
*/

    @Modifying
    @Query("update Social t set t.reactionType ='', t.reactionValue = 0 where t.userId = :userId and t.commentId = :commentId ")
    void deleteCommentReaction(@Param("userId")String userId,@Param("commentId")String commentId);


    @Modifying
    @Query("update Social t set t.reactionType= :reactionType , t.reactionValue = :reactionValue where t.userId = :userId and t.commentId = :commentId")
    void changeCommentReaction(@Param("userId")String userId,@Param("commentId")String commentId,@Param("reactionType")String reactionType,@Param("reactionValue")int reactionValue);



    @Modifying
    @Query("select count(*),reactionType from Social t where t.questionId = :questionId GROUP BY  t.reactionType")
    List<Object[]> getReactionCountQuestion(@Param("questionId")String questionId);
//    Map<Integer, String> getReactionCountQuestion(@Param("questionId")int questionId);
   // List<CountStruct> getReactionCountQuestion(@Param("questionId")int questionId);

    @Modifying
    @Query("select count(*),reactionType from Social t where t.answerId = :answerId GROUP BY t.reactionType")
    List<Object[]>  getReactionCountAnswer(@Param("answerId")String answerId);
//    Map<Integer, String> getReactionCountAnswer(@Param("answerId")int answerId);
    //List<CountStruct> getReactionCountAnswer(@Param("answerId")int answerId);

    @Modifying
    @Query("select count(*),reactionType from Social t where t.commentId =:commentId GROUP BY t.reactionType")
    List<Object[]> getReactionCountComment(@Param("commentId")String commentId);
//    List<CountStruct> getReactionCountComment(@Param("commentId")int commentId);



}
