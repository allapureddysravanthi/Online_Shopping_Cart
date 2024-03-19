package com.jsp.shoppingCart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.CartDao;
import com.jsp.shoppingCart.Dao.CustomerDao;
import com.jsp.shoppingCart.Dto.Cart;
import com.jsp.shoppingCart.Dto.Customer;
import com.jsp.shoppingCart.Dto.Item;

@Controller
public class CartController {

	@Autowired
	CartDao cdao;
	
	@Autowired
	CustomerDao custdao;
	
	@RequestMapping("/fetchitemsfromcart")
	public ModelAndView fetchItemsFromCart(HttpSession session)
	{
		Customer c=(Customer) session.getAttribute("customerinfo");
		Customer customer=custdao.findCustomerById(c.getId());
		Cart cart=customer.getCart();
		List<Item> items=cart.getItems();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("itemslist",items);
		mav.addObject("totalprice",cart.getTotalPrice());
		mav.setViewName("displaycartitemstocustomer");
		return mav;
	
	}
}
