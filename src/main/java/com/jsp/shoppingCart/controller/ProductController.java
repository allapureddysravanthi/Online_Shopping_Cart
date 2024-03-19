package com.jsp.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingCart.Dao.MerchantDao;
import com.jsp.shoppingCart.Dao.ProductDao;
import com.jsp.shoppingCart.Dto.Merchant;
import com.jsp.shoppingCart.Dto.Product;

@Controller
public class ProductController {
	@Autowired
	ProductDao pdao;
	
	@Autowired
	MerchantDao mdao;     //to update merchant
	
	@RequestMapping("/addproduct")
	public ModelAndView addproduct()
	{
		Product p=new Product();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productobj",p);  //productobj is the key
		mav.setViewName("productform");
		return mav;
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj")Product p,HttpSession session) {
	//when we need to fetch the object data we use modelattribute
		Merchant merchant =(Merchant)session.getAttribute("merchantinfo");
		List<Product> products=merchant.getProducts();
		if(products.size()>0) {
			products.add(p);                   //if else conditions are used to check whether the merchant add products or not 
			merchant.setProducts(products);   
		}else {
			List<Product>productslist=new ArrayList<Product>();
			productslist.add(p);
			
			merchant.setProducts(productslist);
		}
		
		pdao.saveProduct(p);
		mdao.updateMerchant(merchant);
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","data saved successfully");
		mav.setViewName("merchantoptions");
		
		return mav;		
		
}
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id")int id,HttpSession session) {
		
		Merchant merchant=(Merchant) session.getAttribute("merchantinfo");
		
		Merchant m=mdao.deleteProductFromMerchant(merchant.getId(), id);
		mdao.updateMerchant(m);
		pdao.deleteProductById(id);
		session.setAttribute("merchantinfo", m);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("viewallproducts");
		return mav;
		
	}
	@RequestMapping("/displayproducts")
	public ModelAndView displayProducts()
	{
		List<Product> products=pdao.fetchallproduct();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewsallproductstocustomer");
		return mav;
		
	}
	@RequestMapping("/displayproductsbybrand")
	public ModelAndView displayProductsByBrand(ServletRequest req)
	{
		String brand=req.getParameter("brand");	
		List<Product> products=pdao.findproductbybrand(brand);
		ModelAndView mav=new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewsallproductstocustomer");		
		return mav;		
	}
}