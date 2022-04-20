package com.ty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.Product;
@Repository
public class ProductDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;

	public void saveProduct(Product product) {
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
	}

	public void updateProduct(Product product) {
		entityTransaction.begin();
		entityManager.merge(product);
		entityTransaction.commit();
	}

	public Product getProductById(int id) {
		Product product = entityManager.find(Product.class, id);
		return product;
	}

	public boolean deleteProduct(int id) {
		Product product=entityManager.find(Product.class, id);
		if(product!=null) {
			entityTransaction.begin();
			entityManager.remove(product);
			entityTransaction.commit();
			return true;
		}else {
			return false;
		}
				
	}

	public List<Product> getProducts() {
		Query query = entityManager.createQuery("select product from Product product");
		return query.getResultList();
	}

}
