package com.ty.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.dto.Offer;
import com.ty.dto.Product;

@Repository
public class OfferDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;

	public void saveOffer(Offer offer) {
		entityTransaction.begin();
		offer.setDate(LocalDate.now());
		offer.setStatus("valid");
		entityManager.persist(offer);
		entityTransaction.commit();
	}

	public void updateOffer(Offer offer) {
		entityTransaction.begin();
		offer.setDate(LocalDate.now());
		entityManager.merge(offer);
		entityTransaction.commit();
	}

	public Offer getOfferById(int id) {
		Offer offer = entityManager.find(Offer.class, id);
		return offer;
	}

	public boolean deleteOffer(int id) {
		Offer offer = getOfferById(id);
		if (offer != null) {
			entityManager.remove(offer);
			return true;
		} else {
			return false;
		}
	}

	public List<Offer> getOffer() {
		Query query = entityManager.createQuery("select offer from Offer offer");
		return query.getResultList();
	}
	public Offer findOfferByCouponcode(String coupon) {
		Query query=entityManager.createQuery("select offer from Offer offer where offer.coupenCode=?1");
		query.setParameter(1, coupon);
		ArrayList<Offer> offers = (ArrayList<Offer>)query.getResultList();
		if(offers!=null && offers.size()>0) {
			return offers.get(0);
		}else
			return null;
	}
}
