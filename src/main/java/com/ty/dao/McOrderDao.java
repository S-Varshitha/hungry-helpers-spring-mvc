package com.ty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.McOrder;

@Repository
public class McOrderDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;

	public void createOrder(McOrder mcOrder) {
		entityTransaction.begin();
		entityManager.persist(mcOrder);
		entityTransaction.commit();
	}

	public List<McOrder> getAllOrders() {
		Query query = entityManager.createQuery("select orders from McOrder orders");
		return query.getResultList();
	}

	public McOrder getOderById(int id) {
		return entityManager.find(McOrder.class, id);
	}

	public void updateOrder(McOrder mcOrder) {
		entityTransaction.begin();
		entityManager.merge(mcOrder);
		entityTransaction.commit();
	}

	public boolean deleteOrder(int id) {
		McOrder mcOrder = entityManager.find(McOrder.class, id);
		if (mcOrder != null) {
			entityTransaction.begin();
			entityManager.remove(mcOrder);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}

	public void updateStatusByStaff(int id) {
		McOrder mcOrder = getOderById(id);
		Query query = entityManager.createQuery("Update  McOrder  Set status=?2 where order_id=?1");
		query.setParameter(1, id);
		if (mcOrder.getStatus().equals("prepared") || mcOrder.getStatus().equals("delivered")) {
			query.setParameter(2, "delivered");
		} else {
			query.setParameter(2, "preparing");
		}
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}

	public void updateStatusByChef(int id) {
		McOrder mcOrder = getOderById(id);
		Query query = entityManager.createQuery("Update  McOrder  Set status=?2 where order_id=?1");
		query.setParameter(1, id);
		if(mcOrder.getStatus().equals("preparing") || mcOrder.getStatus().equals("prepared")) {
		query.setParameter(2, "prepared");
		}else if(mcOrder.getStatus().equals("delivered")){
			query.setParameter(2, "delivered");
		}
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}

	public void databaseRefresh(List<McOrder> mcOrders) {
		for (McOrder mcOrder : mcOrders) {
			entityManager.refresh(mcOrder);
		}
	}

	public List<McOrder> history() {
		Query query = entityManager.createQuery("select mc from McOrder mc");
		entityTransaction.begin();
		List<McOrder> mcOrders = query.getResultList();
		entityTransaction.commit();
		return mcOrders;
	}

	public void clearHistory() {
		Query query = entityManager.createQuery("delete from Item");
		Query query1 = entityManager.createQuery("delete from McOrder");
		entityTransaction.begin();
		query.executeUpdate();
		query1.executeUpdate();
		entityTransaction.commit();
	}

}