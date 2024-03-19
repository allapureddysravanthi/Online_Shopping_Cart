package com.jsp.shoppingCart.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingCart.Dto.Cart;



@Repository
public class CartDao {
	
	@Autowired
	EntityManagerFactory emf;
	 
	public void saveCart(Cart cart)
	{
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(cart);
		et.commit();
	}
	public void updateCart(Cart c)
	{
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(c);
		et.commit();
	}
	public void deleteCartById(int id)
	{
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Cart c=em.find(Cart.class,id);
		et.begin();
		em.persist(c);
		et.commit();
		
	}
	public CartDao findCartById(int id)
	{
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		CartDao c=em.find(CartDao.class,id);

		if(c!=null)
			return c;
		else
			return null;
		
	}
	public Cart removeAllItemFromCart(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Cart c=em.find(Cart.class, id);
		c.setItems(null);
		c.setTotalPrice(0);
		
		et.begin();
		em.merge(c);
		et.commit();
		return null;
		
	}
	


}