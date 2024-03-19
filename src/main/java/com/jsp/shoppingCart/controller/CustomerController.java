package com.jsp.shoppingCart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.CustomerDao;
import com.jsp.shoppingCart.Dto.Customer;


@Controller
public class CustomerController {
	@Autowired
	CustomerDao cdao;
	
    @RequestMapping("/addcustomer")
	public ModelAndView addCustomer()
	{
    	 Customer  c=new Customer();
    	 ModelAndView mav=new  ModelAndView();
    	 mav.addObject("Customerobj",c);
    	 mav.setViewName("CustomerForm");
    	 return mav;
	}
    @RequestMapping("/SaveCustomer")
    public ModelAndView saveCustomer(@ModelAttribute("Customerobj") Customer c)
    {
         cdao.saveCustomer(c);
    	 ModelAndView mav=new  ModelAndView();
    	 mav.addObject("message","data saved successfully");
    	 mav.setViewName("CustomerLogin");
    	 return mav;
    }
    @RequestMapping("/CustomerLogin")
	public ModelAndView login(ServletRequest req,HttpSession session)
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		System.out.println(email);
        System.out.println(password);
		Customer c=cdao.login(email, password);
		ModelAndView mav=new ModelAndView();
		if(c!=null)
		{
			mav.addObject("msg"," successfully loged in ");
			mav.setViewName("CustomerOptions");
			session.setAttribute("customerinfo", c); 
			return mav;			
		}
		else {
			mav.addObject("msg","customer provide invalid credentials");
			mav.setViewName("CustomerLogin" );
			return mav;
		}
		
	}

}
