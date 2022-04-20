package com.ty.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ty.dao.ItemDao;
import com.ty.dao.McOrderDao;
import com.ty.dao.OfferDao;
import com.ty.dao.ProductDao;
import com.ty.dao.UserDao;
import com.ty.dto.Item;
import com.ty.dto.McOrder;
import com.ty.dto.Offer;
import com.ty.dto.Product;
import com.ty.dto.User;
import com.ty.service.OrderService;

@RestController
public class McOrderController {
	@Autowired
	ProductDao productDao;
	@Autowired
	McOrderDao mcOrderDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	OrderService orderService;
	@Autowired
	OfferDao offerDao;

	HttpSession httpSession;

	@GetMapping("/createorder")
	public ModelAndView createOrder(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView andView = new ModelAndView();
		List<Item> items = new ArrayList<Item>();
		List<Product> products = productDao.getProducts();
		httpSession = req.getSession();
		httpSession.setAttribute("customername", req.getParameter("customername"));
		httpSession.setAttribute("couponcode", req.getParameter("couponcode"));
		httpSession.setAttribute("items", items);
		andView.addObject("item", new Item());
		andView.addObject("products", products);
		andView.setViewName("create_order.jsp");
		return andView;
	}

	@PostMapping("/updateorder")
	public ModelAndView updateProduct(@ModelAttribute Item item) {
		McOrder mcOrder = (McOrder) httpSession.getAttribute("mcOrder");
		List<Item> items = (List<Item>) httpSession.getAttribute("items");
		int product_id = item.getId();
		Item item2 = new Item();
		Product product = productDao.getProductById(product_id);
		item2.setProduct(product);
		item2.setCost(product.getCost());
		item2.setName(product.getName());
		item2.setDescription(product.getDescription());

		item2.setQuantity(item.getQuantity());
		items.add(item2);
		List<Product> products = productDao.getProducts();
		httpSession.setAttribute("items", items);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("create_order.jsp");
		andView.addObject("item", new Item());
		andView.addObject("products", products);

//		for (Item item3 : items) {
//			System.out.println(item3.getQuantity());
//
//		}
		return andView;
	}

	@GetMapping("conformorder")
	public ModelAndView conformOrder() {

		McOrder mcOrder = new McOrder();
		mcOrder.setDate(LocalDate.now());
		mcOrder.setTime(LocalTime.now());
		mcOrder.setStatus("preparing");
		List<Item> items = (List<Item>) httpSession.getAttribute("items");
		mcOrder.setItems(items);
		mcOrder.setCust_name((String) httpSession.getAttribute("customername"));
		String coupon = (String) httpSession.getAttribute("couponcode");
		int userId = (Integer) (httpSession.getAttribute("userId"));
		User user = userDao.getUserById(userId);
		Offer offer = offerDao.findOfferByCouponcode(coupon);
		if (offer == null) {
			mcOrder.setOffer(null);
		} else {
			mcOrder.setOffer(offer);
		}
		mcOrder.setUser(user);
		mcOrderDao.createOrder(mcOrder);
		ArrayList<Item> items2 = new ArrayList<Item>();

		for (Item item : items) {
			item.setMcOrder(mcOrder);
			items2.add(item);
		}

		for (Item item : items2) {
			itemDao.createItem(item);
		}

		ModelAndView andView = new ModelAndView();
		andView.setViewName("staff.jsp");
		httpSession.setAttribute("customername", null);
		httpSession.setAttribute("items", null);
		return andView;
	}

	@RequestMapping("billgen")
	public ModelAndView billGenerating(@RequestParam int id) {
		System.out.println(id);
		McOrder mcOrder = mcOrderDao.getOderById(id);
		mcOrder = orderService.updateOrder(mcOrder);
		List<Item> items = mcOrder.getItems();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("bill.jsp");
		andView.addObject("mcOrder", mcOrder);
		andView.addObject("items", items);
		return andView;
	}

	@GetMapping("showkart")
	public ModelAndView showKart() {
		List<McOrder> mcOrders = mcOrderDao.getAllOrders();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("showorder.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;
	}

	@RequestMapping("cancelorder")
	public ModelAndView deleteOrder(@RequestParam int id) {
		McOrder mcOrder = mcOrderDao.getOderById(id);
		List<Item> items = mcOrder.getItems();
		for (Item item : items) {
			itemDao.deleteItem(item.getId());
		}
		mcOrderDao.deleteOrder(id);
		List<McOrder> mcOrders = mcOrderDao.getAllOrders();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("showorder.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;
	}

	@RequestMapping("updatestatus")
	public ModelAndView updateStatusForStaff(@RequestParam int id) {
		mcOrderDao.updateStatusByStaff(id);
		List<McOrder> mcOrders1 = mcOrderDao.getAllOrders();
		mcOrderDao.databaseRefresh(mcOrders1);
		List<McOrder> mcOrders = mcOrderDao.getAllOrders();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("showorder.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;
	}

	@RequestMapping("updatestatuschef")
	public ModelAndView updateStatusForChef(@RequestParam int id) {
		mcOrderDao.updateStatusByChef(id);
		List<McOrder> mcOrders1 = mcOrderDao.getAllOrders();
		mcOrderDao.databaseRefresh(mcOrders1);
		List<McOrder> mcOrders = mcOrderDao.getAllOrders();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("show_chef.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;

	}

	@GetMapping("showchef")
	public ModelAndView chef() {
		List<McOrder> mcOrders = mcOrderDao.getAllOrders();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("show_chef.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;
	}

	@GetMapping("history")
	public ModelAndView history() {
		List<McOrder> mcOrders = mcOrderDao.history();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("showhistory.jsp");
		andView.addObject("mcOrders", mcOrders);
		return andView;
	}

	@GetMapping("clearhistory")
	public ModelAndView clearHisotry() {
		mcOrderDao.clearHistory();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("admin.jsp");
		return andView;

	}

	@GetMapping("getitemsbyid")
	public ModelAndView getItemByOrderId(@RequestParam int orderId) {
		McOrder mcOrder = mcOrderDao.getOderById(orderId);
		List<Item> items = mcOrder.getItems();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view_item.jsp");
		modelAndView.addObject("itemsList", items);
		return modelAndView;
	}

}