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
public class CartsController {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private BookMapper bookMapper;

	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		// 현재 사용자
		int currentUserId = userMapper.getUserIdByEmail(principal.getName());

		// 카트에
		Cart cart = userMapper.getCart(currentUserId);
		if (cart != null) {
			// 담긴 아이템
			List<Item> items = cartMapper.getItems(cart);
			for (Item i : items) {
				i.setBook(bookMapper.getBook(i.getBook_id())); // 아이템이 가리키는 책
			}
			// 합계
			int sum = 0;
			for (Item i : items) {
				sum += i.getPrice() * i.getAmount();
			}
			cart.setTotalPrice(sum);
			model.addAttribute("cart", cart);
			model.addAttribute("items", items);
		}
		return "carts/index";
	}

	@RequestMapping(value = "/carts/add", method = RequestMethod.POST)
	public String add(@ModelAttribute Item item, Principal principal) {
		// 현재 사용자
		int currentUserId = userMapper.getUserIdByEmail(principal.getName());

		// 쇼핑카트(현재 사용자 소유) 번호를 아이템에 저장
		if (!userMapper.hasCart(Cart.SHOPPING, currentUserId)) {
			int cartId = cartMapper.create(new Cart(Cart.SHOPPING, currentUserId));
			item.setCart_id(cartId);
		} else {
			item.setCart_id(userMapper.getCart(currentUserId).getId());
		}
		// 쇼핑카트에 아이템을 담음
		cartMapper.add(item);
		return "redirect:/carts";
	}
}