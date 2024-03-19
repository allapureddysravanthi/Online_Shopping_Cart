package com.jsp.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.CartDao;
import com.jsp.shoppingCart.Dao.CustomerDao;
import com.jsp.shoppingCart.Dao.OrdersDao;
import com.jsp.shoppingCart.Dao.ProductDao;
import com.jsp.shoppingCart.Dto.Cart;
import com.jsp.shoppingCart.Dto.Customer;
import com.jsp.shoppingCart.Dto.Item;
import com.jsp.shoppingCart.Dto.Orders;
import com.jsp.shoppingCart.Dto.Product;

@Controller
public class OrdersController {
    @Autowired
	OrdersDao odao;
    
    @Autowired
    CustomerDao custdao;
    
    @Autowired
    ProductDao pdao;
    
    @Autowired
    CartDao cdao;
    
    @RequestMapping("/addorder")
    public ModelAndView addOrder()
    {
    	Orders o=new Orders();
    	ModelAndView mav=new ModelAndView();
    	mav.addObject("ordersobj",o);
    	mav.setViewName("ordersform");
    	return mav;  	
    }  
    
    @RequestMapping("/saveorder")
    public ModelAndView saveOrder(@ModelAttribute("ordersobj")Orders o,HttpSession session)
    {
        Customer c=	(Customer)session.getAttribute("customerinfo");
        Customer customer= custdao.findCustomerById(c.getId());
        Cart cart=customer.getCart();
        
        List<Item>items=cart.getItems();
        
        
       
        List<Item>itemsList=new ArrayList<>();
        List<Item> itemsWithGreaterQuantity =new ArrayList<>();
        for(Item i:items)
        {
        	Product p=pdao.findProductById(i.getP_id());
        	if(i.getQuantity()<p.getStock())
        	{
        		itemsList.add(i);
        		p.setStock(p.getStock()-i.getQuantity());
        		pdao.updateProduct(p);
        	}
        	else
        	{
        		itemsWithGreaterQuantity .add(i);
        	}
        }
        o.setItems(itemsList);
        double totalpriceoforder=0;
        
        for(Item i: itemsList)
        {
        	totalpriceoforder =totalpriceoforder+i.getPrice();
        }
        o.setTotalprice(totalpriceoforder);
        
        cart.setItems( itemsWithGreaterQuantity );
        double totalprice=0;
        for(Item i: itemsWithGreaterQuantity )
        {
        	totalprice =totalprice+i.getPrice();
        }
        List<Orders> orders=customer.getOrders();
        if(orders.size()>0)
        {
        	orders.add(o);
        	customer.setOrders(orders);
        }
        else
        {
        	 List<Orders> orders1=customer.getOrders(); //when customer making order at first
        	 customer.setOrders(orders1);               // time then this else block will execute
        	 orders1.add(o);
        	 
        }
        customer.setCart(cart);
        cdao.updateCart(cart); 
        odao.saveOrders(o);
        custdao.updateCustomer(customer);
         
         ModelAndView mav=new ModelAndView();
         mav.addObject("msg","order placed successfully");
     	 mav.addObject("orderdetails",o);
     	 mav.setViewName("CustomerBill");
     	return mav;  
    }   
}
