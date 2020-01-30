package com.mycompany.influencer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.mapper.ReviewMapper;
import com.mycompany.vo.Review;

@Controller
public class ReviewsController {
	@Autowired
	private ReviewMapper reviewMapper;

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public String create(@ModelAttribute Review review) {
		reviewMapper.create(review);
		return "redirect:/books/" + review.getBookId();
	}
}