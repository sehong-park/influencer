package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.Cart;
import com.mycompany.vo.Item;

public interface CartMapper {
	@Insert("insert into carts (user_id, status) values (#{user_id}, #{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int create(Cart cartVO);

	@Insert("insert into items (cart_id, book_id, amount) values (#{cart_id}, #{book_id}, #{amount})")
	void add(Item item);

	@Select("select * from items where cart_id = #{id}")
	List<Item> getItems(Cart cart);

	@Update("update carts set status = 1 where id = #{id}")
	void order(Cart cart);
}