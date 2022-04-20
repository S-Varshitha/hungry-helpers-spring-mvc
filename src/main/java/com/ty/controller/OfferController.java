package com.ty.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ty.dao.OfferDao;
import com.ty.dto.Offer;

@RestController
public class OfferController {
	@Autowired
	OfferDao offerDao;

	@GetMapping("/readoffer")
	public ModelAndView readOffer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("create_offer.jsp");
		modelAndView.addObject("offer", new Offer());
		return modelAndView;
	}

	@PostMapping("/createoffer")
	public ModelAndView createOffer(@ModelAttribute Offer offer) {
		offerDao.saveOffer(offer);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin.jsp");
		return modelAndView;
	}
	@GetMapping("viewoffers")
	public ModelAndView viewOffers() {
		List<Offer> offers=offerDao.getOffer();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("view_offer.jsp");
		modelAndView.addObject("offers",offers);
		return modelAndView;
	}
	@GetMapping("editoffer")
	public ModelAndView editOffer(@RequestParam int id) {
		Offer offer=offerDao.getOfferById(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("edit_offer.jsp");
		modelAndView.addObject("offer", offer);
		return modelAndView;
	}
	@GetMapping("updateoffer")
	public ModelAndView updateOffer(@ModelAttribute Offer offer) {
		offerDao.updateOffer(offer);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin.jsp");
		return modelAndView;
	}
	@RequestMapping("deleteoffer")
	public ModelAndView deleteOffer(@RequestParam int id) {
		offerDao.deleteOffer(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin.jsp");
		return modelAndView;
	}
}
