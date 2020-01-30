package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mycompany.vo.Paging;
import com.mycompany.vo.Review;
import com.mycompany.vo.User;

public interface ReviewMapper {
	@Insert("INSERT INTO reviews (text, book_id, user_id, rating) VALUES (#{text}, #{bookId}, #{userId}, #{rating})")
	void create(Review review);

	@Select("SELECT * FROM reviews WHERE book_id = #{bookId} ORDER BY id DESC LIMIT #{p.rows} OFFSET (#{p.index} - 1) * #{p.rows}")
	@Results(value = {
	        @Result(property = "id", column = "id"),
	        @Result(property = "text", column = "text"),
	        @Result(property = "bookId", column = "book_id"),
	        @Result(property = "userId", column = "user_id"),
	        @Result(property = "user", column = "id", javaType = User.class, one = @One(select = "getUserById"))
	})
	List<Review> getReviews(@Param("bookId") int bookId, @Param("p") Paging paging);
	
	@Select("select * from users where id = #{userId}")
    public User getUserById(int userId);

	@Select("SELECT COUNT(*) FROM reviews WHERE book_id = #{bookId}")
	int getReviewsCnt(int id);
}
