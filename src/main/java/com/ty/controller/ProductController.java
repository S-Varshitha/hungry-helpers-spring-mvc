package com.ty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ty.dao.ProductDao;
import com.ty.dto.Product;
import com.ty.dto.User;

@RestController
public class ProductController {

	@Autowired
	ProductDao productDao;

	@GetMapping("/readproduct")
	public ModelAndView readProduct() {
		ModelAndView andView = new ModelAndView();
		andView.setViewName("create_product.jsp");
		andView.addObject("product", new Product());
		return andView;
	}

	@PostMapping("/createproduct")
	public ModelAndView createProduct(@ModelAttribute Product product) {
		productDao.saveProduct(product);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("admin.jsp");
		return andView;
	}

	@GetMapping("/viewproduct")
	public ModelAndView getProducts() {
		List<Product> products = productDao.getProducts();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("view_product.jsp");
		andView.addObject("products", products);
		return andView;
	}

	@GetMapping("/editproduct")
	public ModelAndView getProductToEdit(@RequestParam int id) {
		Product product = productDao.getProductById(id);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("edit_product.jsp");
		andView.addObject("product", product);
		return andView;
	}

	@PostMapping("/updateproduct")
	public ModelAndView udateProduct(@ModelAttribute Product product) {
		productDao.updateProduct(product);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("admin.jsp");
		return andView;
	}

	@GetMapping("/deleteproduct")
	public ModelAndView getDeleteProduct(@RequestParam int id) {
		productDao.deleteProduct(id);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("admin.jsp");
		return andView;
	}
}
