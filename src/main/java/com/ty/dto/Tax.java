package com.ty.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tax_id;
	private double gst;
	private double ser_tax;

	public int getTax_id() {
		return tax_id;
	}

	public void setTax_id(int tax_id) {
		this.tax_id = tax_id;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getSer_tax() {
		return ser_tax;
	}

	public void setSer_tax(double ser_tax) {
		this.ser_tax = ser_tax;
	}

}
