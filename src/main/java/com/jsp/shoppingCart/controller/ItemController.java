package com.jsp.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.CartDao;
import com.jsp.shoppingCart.Dao.CustomerDao;
import com.jsp.shoppingCart.Dao.ItemDao;
import com.jsp.shoppingCart.Dao.ProductDao;
import com.jsp.shoppingCart.Dto.Cart;
import com.jsp.shoppingCart.Dto.Customer;
import com.jsp.shoppingCart.Dto.Item;
import com.jsp.shoppingCart.Dto.Product;

@Controller
public class ItemController {
	      @Autowired
          ItemDao idao;
	      
	      @Autowired
	      ProductDao pdao;
	      
	      @Autowired
	      CartDao cdao;
	      
	      @Autowired
	      CustomerDao custdao;
	      
	      @RequestMapping("/additem")
	 public ModelAndView saveItem(@RequestParam("id")int id)
	 {
		 Product p=pdao.findProductById(id);
		 ModelAndView mav=new ModelAndView();
		 mav.addObject("prodobj",p);
    	 mav.setViewName("itemform");
    	 return mav;
	 }
	      @RequestMapping("/additemtocart")
	      public ModelAndView addItemToCart(ServletRequest req,HttpSession session)
	      {
	    	  int product_id=Integer.parseInt(req.getParameter("id"));
	    	  String brand=req.getParameter("brand");
	    	  Double price=Double.parseDouble(req.getParameter("price"));
	    	  String model=req.getParameter("model");
	    	  String category=req.getParameter("category");
	    	  int quantity=Integer.parseInt(req.getParameter("quantity"));
	    	  
	    	  Item item=new Item();
	    	  item.setBrand(brand);
	    	  item.setCategory(category);
	    	  item.setModel(model);
	    	  item.setQuantity(quantity);
	    	  item.setP_id(product_id);
	    	  item.setPrice(quantity*price);
	    	  
	    	  Customer customer=(Customer) session.getAttribute("customerinfo");
	    	  Cart c=customer.getCart();
	    	  if(c==null)
	    	  {
	    		  double totalprice=0;
	    		  Cart cart=new Cart();
	    		  List<Item> items=new ArrayList<>();
	    		  items.add(item);
	    		  
	    		  cart.setItems(items);
	    		  cart.setName(customer.getName());   //if there is no cart is created that time if block will execute
	    		  
	    		  for(Item i:items)
	    		  {
	    			  totalprice=totalprice+i.getPrice();
	    		  }
	    		  cart.setTotalPrice(totalprice);
	    		  customer.setCart(cart);
	    		  
	    		  idao.saveItem(item);
	    		  cdao.saveCart(cart);
	    		  custdao.updateCustomer(customer);
	    	  }
	    	  else
	    	  {
	    		  List<Item> items= c.getItems();
	    		  if(items.size()>0)
	    		  {
	    			  items.add(item);
	    			  c.setItems(items);
	    			  double totalprice=0;
	    			  for(Item i:items)
	    			  {
	    				  totalprice =totalprice+i.getPrice();
	    			  }
	    			  c.setTotalPrice(totalprice);
	    			  customer.setCart(c);
	    			  
	    			  idao.saveItem(item);
	    			  cdao.updateCart(c);
	    			  custdao.updateCustomer(customer);
	    		  }
	    		  else
	    		  {
	    			  List<Item> itemslist=new ArrayList<>();
	    			  itemslist.add(item);
	    			  c.setItems(itemslist);
	    			  c.setTotalPrice(item.getPrice());
	    					
	    		  }
	    		  ModelAndView mav=new ModelAndView();
	    	    	 mav.setViewName("redirect://displayproducts");
	    	    	 return mav;
	    	  }
			return null;
	      }
}
