package com.jsp.shoppingCart.Dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.shoppingCart.Dto.Product;


@Repository   //@Repository is the subclass of @Component.
public class ProductDao {
	
	@Autowired      //is used to achieve automatic dependency injection.
	EntityManagerFactory emf;
	
	 public void saveProduct(Product p)
	 {
		 EntityManager em=emf.createEntityManager();
		 EntityTransaction et=em.getTransaction();
		 
		 et.begin();
		 em.persist(p);
		 et.commit();		 
	 }
	 
	public void updateProduct(Product p) {
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();

	et.begin();
	em.merge(p);
	et.commit();
	}
	public void deleteProductById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();		
		Product p=em.find(Product.class,id);
		et.begin();
		em.remove(p);
		et.commit();
	}
	public Product  findProductById(int id)
	{
		EntityManager em=emf.createEntityManager();
		
		Product p=em.find(Product.class, id);
		if(p!=null)
		{
			return p;			
		}
		else {
			return null;
		}		
	}
	public List<Product>fetchallproduct(){
		EntityManager em=emf.createEntityManager();
		Query query=em.createQuery("select p from Product p");
		return query.getResultList();
	}
	public List<Product>findproductbybrand(String brand){
		EntityManager em=emf.createEntityManager();
		Query query=em.createQuery("select p from Product p where p.brand=?1");
		query.setParameter(1, brand );
		return query.getResultList();
	}
	
	
	
}
