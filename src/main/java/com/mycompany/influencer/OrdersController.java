package com.mycompany.influencer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.mapper.BookMapper;
import com.mycompany.mapper.CartMapper;
import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.Cart;
import com.mycompany.vo.Item;

@Controller
public class OrdersController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private BookMapper bookMapper;

	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public String create(@ModelAttribute Cart cart) {
		cartMapper.order(cart);
		return "redirect:/orders";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		// 현재 사용자
		int currUserId = userMapper.getUserIdByEmail(principal.getName());
		
		// 주문 목록
		List<Cart> orders = userMapper.getOrders(currUserId);
		// 주문별 아이템
		for (Cart order : orders) {
			order.setItems(cartMapper.getItems(order));
			// 아이템-책
			int sum = 0;
			for (Item i : order.getItems()) {
				i.setBook(bookMapper.getBook(i.getBook_id()));
				sum += i.getPrice() * i.getAmount();
			}
			order.setTotalPrice(sum);
		}
		model.addAttribute("orders", orders);
		return "orders/index";
	}
}