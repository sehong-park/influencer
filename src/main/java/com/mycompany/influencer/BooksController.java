package com.mycompany.influencer;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.helper.FileHelper;
import com.mycompany.mapper.BookMapper;
import com.mycompany.mapper.ReviewMapper;
import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.Book;
import com.mycompany.vo.Paging;
import com.mycompany.vo.Review;

@Controller
public class BooksController {

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String index(Model model) {
		List<Book> books = bookMapper.getList();
		model.addAttribute("books", books);
		return "books/index";
	}

	@RequestMapping(value = "/books/new", method = RequestMethod.GET)
	public String newBook() {
		return "books/new";
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String create(@ModelAttribute Book book, @RequestParam MultipartFile file, HttpServletRequest request) {
		String fileUrl = FileHelper.upload("/uploads", file, request);
		book.setImage(fileUrl);
		bookMapper.create(book);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		Book book = bookMapper.getBook(id);
		model.addAttribute("book", book);
		return "books/edit";
	}

	@RequestMapping(value = "/books/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Book book, @RequestParam MultipartFile file, HttpServletRequest request) {
		String fileUrl = FileHelper.upload("/uploads", file, request);
		book.setImage(fileUrl);
		bookMapper.update(book);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		bookMapper.delete(id);
		return "redirect:/books";
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model, Principal principal, Paging paging, @RequestParam(value="pageNum", required=false) Optional<Integer> pageNum) {
		Book book = bookMapper.getBook(id);
		model.addAttribute("book", book);
		
		// 기존 리뷰들
		if (pageNum.isPresent()) {
			paging.setIndex(pageNum.get());
		}
	    List<Review> reviews = reviewMapper.getReviews(id, paging);
	    model.addAttribute("reviews", reviews);
	    
	    // 페이징
	    paging.setTotal(reviewMapper.getReviewsCnt(id));
	    model.addAttribute("paging", paging);
		
		// 폼 태그에서 modelAttribute="review" 속성을 읽어올 수 있어야함.
		if (!model.containsAttribute("review")) {
			Review review = new Review();
			review.setBookId(id);
			String email = principal.getName();
			int userId = userMapper.getUserIdByEmail(email);
			review.setUserId(userId);
			model.addAttribute("review", review);
		}
		
		// rating opts
		Map<Integer, String> ratingOptions = new HashMap<Integer, String>();
		ratingOptions.put(5, "★★★★★");
		ratingOptions.put(4, "★★★★☆");
		ratingOptions.put(3, "★★★☆☆");
		ratingOptions.put(2, "★★☆☆☆");
		ratingOptions.put(1, "★☆☆☆☆");
		ratingOptions.put(0, "☆☆☆☆☆");
		model.addAttribute("ratingOptions", ratingOptions);
		return "books/show";
	}
	
	@RequestMapping(value ="/books/search", method = RequestMethod.GET)
	public String search(@RequestParam String query, Model model) {
		List<Book> books = bookMapper.search(query);
		model.addAttribute("books", books);
		return "books/index";
	}
}
