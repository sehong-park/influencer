package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mycompany.vo.Authority;
import com.mycompany.vo.Cart;
import com.mycompany.vo.User;

public interface UserMapper {
	@Insert("INSERT INTO users (email, password, enabled) " + "VALUES (#{email}, #{password}, true)")
	public boolean insertUser(User user);

	@Insert("INSERT INTO authorities (email, authority) " + "VALUES (#{email}, #{authority})")
	public boolean insertAuthority(@Param("email") String email, @Param("authority") String authority);

	@Select("SELECT id, email FROM users")
	@Results(value = {
	        @Result(property = "id", column = "id"),
	        @Result(property = "email", column = "email"),
	        @Result(property = "authorities", column = "email", javaType = List.class, many = @Many(select = "selectAuthority"))
	})
	public List<User> selectUsers();
	
	@Select("SELECT email, authority "
            + "FROM authorities "
            + "WHERE email = #{email}")
    public List<Authority> selectAuthority(String email);

	
	@Select("SELECT id, email FROM users WHERE id = #{userId}")
	@Results(value = {
	        @Result(property = "id", column = "id"),
	        @Result(property = "email", column = "email"),
	        @Result(property = "authorities", column = "email", javaType = List.class, many = @Many(select = "selectAuthority"))
	})
	public User selectUserById(int userId);
	
	@Delete("DELETE FROM authorities WHERE email = #{email} AND authority = #{role}")
	public void deleteAuthority(@Param("email") String email, @Param("role") String role);
	
	@Select("select id from users where email = #{email}")
	public int getUserIdByEmail(String email);
	
	@Select("select exists(select 1 from carts where status = #{status} and user_id = #{user_id})")
    boolean hasCart(@Param("status") int status, @Param("user_id") int user_id);
	
	@Select("select * from carts where user_id = #{userId} and status = 0")
    public Cart getCart(int userId);

	@Select("select * from carts where user_id = #{userId} and status > 0")
    public List<Cart> getOrders(int userId);
}
