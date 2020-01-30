package com.mycompany.influencer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.vo.User;

@Controller
public class SessionsController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "statics/login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "statics/signup";
    }
}
