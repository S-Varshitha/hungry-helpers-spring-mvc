package com.ty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ty.dao.UserDao;
import com.ty.dto.User;
@Controller
public class UserController {
	@Autowired
	UserDao userDao;

	@RequestMapping("loginUser")
	public ModelAndView userLogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.jsp");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, @ModelAttribute User user) {
		User user2 = userDao.validateUser(user.getEmail(), user.getPassword());
		if (user2 != null) {
			if (user2.getRole().equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("userId",user2.getId());
				session.setAttribute("name", user2.getName());
				session.setAttribute("role", user2.getRole());
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("admin.jsp");
				modelAndView.addObject("user", user2);
				modelAndView.addObject("message", user2.getName()+" logged in sucussefully");
				return modelAndView;
			} else if (user2.getRole().equals("staff")) {
				HttpSession session = request.getSession();
				session.setAttribute("userId",user2.getId());
				session.setAttribute("name", user2.getName());
				session.setAttribute("role", user2.getRole());
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("staff.jsp");
				modelAndView.addObject("user", user2);
				modelAndView.addObject("message", user2.getEmail() + " loggedIn successfully");
				return modelAndView;
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("name", user2.getName());
				session.setAttribute("userId",user2.getId());
				session.setAttribute("role", user2.getRole());
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("chef.jsp");
				modelAndView.addObject("user", user2);
				modelAndView.addObject("message", user2.getEmail() + " loggedIn successfully");
				return modelAndView;
			}
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("login.jsp");
			modelAndView.addObject("message", "Invlaid credential");
			return modelAndView;
		}
	}
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		httpSession.invalidate();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("home.jsp");
		return modelAndView;
	}
	
	
	@GetMapping("/readuser")
	public ModelAndView readUser(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		if(httpSession!=null) {
		ModelAndView andView = new ModelAndView();
		andView.setViewName("create_user.jsp");
		andView.addObject("user", new User());
		return andView;
		}else {
			ModelAndView andView = new ModelAndView();
			andView.setViewName("home.jsp");
			andView.addObject("Message","Invalid login");
			return andView;
		}
	}
	
	@PostMapping("/createuser")
	public ModelAndView createUser(@ModelAttribute User user) {
		userDao.createUser(user);
		ModelAndView andView = new ModelAndView();
		andView.setViewName("admin.jsp");
		return andView;
	}
	@RequestMapping("/viewusers")
	public ModelAndView getAllUser() {
		List<User> users=userDao.getAllUser();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("view_user.jsp");
		modelAndView.addObject("userList", users);
		return modelAndView;
	}
	@RequestMapping("edituser")
	public ModelAndView editUser(@RequestParam int id) {
		User user=userDao.getUserById(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("edit_user.jsp");
		modelAndView.addObject("editUser",user);
		return modelAndView;
	}
	@RequestMapping("updateuser")
	public ModelAndView updateUser(@ModelAttribute User editUser) {
		userDao.updateUser(editUser);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message",editUser.getName()+" Updated successfully");
		modelAndView.setViewName("admin.jsp");
		return modelAndView;
	}
	@RequestMapping("deleteuser")
	public ModelAndView deleteUser(@RequestParam int id) {
		User user=userDao.getUserById(id);
		userDao.deleteOrder(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin.jsp");
		modelAndView.addObject("message",user.getName()+" deleted sucessfully");
		return modelAndView;
	}
}
