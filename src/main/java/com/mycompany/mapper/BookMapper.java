package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.Book;

public interface BookMapper {

	@Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
	void create(Book book);

	@Select("select * from books")
	List<Book> getList();

	@Select("select * from books where id = #{id}")
	Book getBook(int id);

	@Update("update books set title = #{title}, author = #{author}, image = #{image} where id = #{id}")
	void update(Book book);

	@Delete("delete from books where id = #{id}")
	void delete(int id);

	@Select("select * from books where title like '%'||#{term}||'%'")
	List<Book> search(String term);
}
