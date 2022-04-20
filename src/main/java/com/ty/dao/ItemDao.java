package com.ty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.Item;
@Repository
public class ItemDao {
	@Autowired
	EntityManager entityManager ;
	@Autowired
	EntityTransaction entityTransaction;

	public void createItem(Item item) {
		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
	}

	public List<Item> getAllItems() {
		Query query = entityManager.createQuery("select item from Item item");
		return query.getResultList();
	}

	public Item getItemById(int id) {
		return entityManager.find(Item.class, id);
	}

	public void updateItem(Item item) {
		entityTransaction.begin();
		entityManager.merge(item);
		entityTransaction.commit();
	}

	public boolean deleteItem(int id) {
		Item item = entityManager.find(Item.class, id);
		if (item != null) {
			entityTransaction.begin();
			entityManager.remove(item);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}
	public List<Item> getAllItemsById(int orderId){
		Query query=entityManager.createQuery("select item from Item item where order_id=?1");
		query.setParameter(1,orderId);
		return query.getResultList();
	}
}
