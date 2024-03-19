package com.jsp.shoppingCart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.MerchantDao;
import com.jsp.shoppingCart.Dto.Merchant;

@Controller
public class MerchantController {
	@Autowired
	MerchantDao dao;	
	@RequestMapping("/addmerchant")
	public ModelAndView addMerchant() {
		Merchant m=new Merchant();// entity class
		
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("merchantobj",m);
		mav.setViewName("MerchantForm");
		return mav;		
	} 
	/**
	 * line 36 will insert data into DB
	 * @param m
	 * @return
	 */
	
	@RequestMapping("/savemerchant")
	public ModelAndView saveMerchant(@ModelAttribute("merchantobj")Merchant m) {
		dao.saveMerchant(m);
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","data saved successfully");
		mav.setViewName("Menu");
		return mav;
	}
	@RequestMapping("/Loginvalidation")
	public ModelAndView login(ServletRequest req,HttpSession session)
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");		
		Merchant m=dao.login(email, password);
		ModelAndView mav=new ModelAndView();
		if(m!=null) {
			mav.addObject("msg","successfully loged in");
			mav.setViewName("merchantoptions");
			session.setAttribute("merchantinfo", m); //here we stored merchant information in session.
			return mav;			
		}
		else {
			mav.addObject("msg","invalid credentials");
			mav.setViewName("MerchantLoginForm");
			return mav;
		}
	}
}