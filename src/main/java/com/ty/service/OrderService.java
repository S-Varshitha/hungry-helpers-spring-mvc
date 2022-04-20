package com.ty.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.dao.McOrderDao;
import com.ty.dao.TaxDao;
import com.ty.dto.Item;
import com.ty.dto.McOrder;
import com.ty.dto.Offer;
import com.ty.dto.Tax;

@Service
public class OrderService {

	@Autowired
	McOrderDao mcOrderDao;
	@Autowired
	TaxDao taxDao;

	public McOrder updateOrder(McOrder mcOrder) {
		double total = 0;
		List<Item> items = mcOrder.getItems();
		for (Item item : items) {
			double cost = item.getProduct().getCost();
			double offer = item.getProduct().getOffer();
			double deducted = (offer / 100) * cost;
			double sum = (cost - deducted) * item.getQuantity();
			total += sum;
		}
		double discount = 0.0;
		LocalDate offerDate = null;
		LocalDate orderDate = null;
		int differceDays = 0;
		if (mcOrder.getOffer() != null) {
			offerDate = mcOrder.getOffer().getDate();
			orderDate = mcOrder.getDate();
			differceDays = (Period.between(offerDate, orderDate).getDays());
			if (mcOrder.getOffer().getStatus().equals("valid") && differceDays <= 30) {
				discount = mcOrder.getOffer().getDiscount();
				mcOrder.getOffer().setStatus("invalid");
			}
		}
		System.out.println("Difference days " + differceDays);
		System.out.println(discount);
		Tax tax = taxDao.getTax();
		double gst = (tax.getGst() / 100) * total;
		double ser_tax = (tax.getSer_tax() / 100) * total;
		System.out.println(gst);
		System.out.println(ser_tax);
		total += gst + ser_tax - discount;
		mcOrder.setTotalCost(total);
		mcOrderDao.updateOrder(mcOrder);
		return mcOrder;
	}

	public void isValidCopon(String copon) {

	}
}