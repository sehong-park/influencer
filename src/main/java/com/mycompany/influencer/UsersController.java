package com.mycompany.influencer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.User;

@Controller
public class UsersController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String create(@ModelAttribute User user) {
		userMapper.insertUser(user);
		userMapper.insertAuthority(user.getEmail(), "ROLE_USER");
		return "redirect:/statics/login";
	}
}