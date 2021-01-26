package com.teamtwo.quora.quorasocial.repository;

import com.teamtwo.quora.quorasocial.Entity.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface QuestionRepository extends CrudRepository<Question,String> {
    List<Question> findByCategoryId(String categoryId);

    List<Question> findByUserId(String str);


   /* @Modifying
    @Query(value="insert into Question q(q.questionString,q.categoryId,q.userId) Values(:questionString,:categoryId,:userId)",nativeQuery = true)
    void addQuestion(@Param("questionString")String questionString,@Param("categoryId")String categoryId,@Param("userId")String userId);
*/

}
