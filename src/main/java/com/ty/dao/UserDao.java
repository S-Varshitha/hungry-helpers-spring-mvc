package com.ty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.User;
@Repository
public class UserDao {
	@Autowired
	EntityManager entityManager ;
	@Autowired
	EntityTransaction entityTransaction;

	public void createUser(User user) {
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}

	public List<User> getAllUser() {
		Query query = entityManager.createQuery("select user from User user");
		return query.getResultList();
	}

	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}

	public void updateUser(User user) {
		entityTransaction.begin();
		entityManager.merge(user);
		entityTransaction.commit();
	}

	public boolean deleteOrder(int id) {
		User user = entityManager.find(User.class, id);
		if (user != null) {
			entityTransaction.begin();
			entityManager.remove(user);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}
	public User validateUser(String email,String password) {
		Query query=entityManager.createQuery("select u from User u where email=?1 and password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> user=query.getResultList();
		if(user!=null && user.size()>0) {
			return user.get(0);
		}else {
			return null;
		}
	}
}
