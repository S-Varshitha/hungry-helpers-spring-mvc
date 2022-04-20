package com.ty.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.Tax;
@Repository
public class TaxDao {

	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;

	public void saveTax(Tax tax) {
		entityTransaction.begin();
		entityManager.persist(tax);
		entityTransaction.commit();
	}

	public void updateTax(Tax tax) {
		entityTransaction.begin();
		entityManager.merge(tax);
		entityTransaction.commit();
	}
	public Tax getTax() {
		return entityManager.find(Tax.class,1);
	}
}
