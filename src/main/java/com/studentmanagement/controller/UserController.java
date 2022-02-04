package com.studentmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentmanagement.dao.UserDAO;
import com.studentmanagement.dto.UserRequestDTO;
import com.studentmanagement.dto.UserResponseDTO;
import com.studentmanagement.model.LoginBean;
import com.studentmanagement.model.SearchBean;
import com.studentmanagement.model.UserBean;

@Controller
public class UserController {

	@Autowired
	private UserDAO dao;

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("LGN001", "bean", new LoginBean());
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("bean") @Validated LoginBean bean, BindingResult br, ModelMap model,
			HttpSession session) {
		if (br.hasErrors()) {
			return "LGN001";
		}
		UserRequestDTO dto = new UserRequestDTO();
		dto.setId(bean.getId());
		List<UserResponseDTO> list = dao.selectUser(dto);
		if (list.size() == 0) {
			model.addAttribute("error", "UserID not found");
			return "LGN001";
		} else {
			if (list.get(0).getPassword().equals(bean.getPassword())) {
				session.setAttribute("userId", list.get(0).getId());
				session.setAttribute("userName", list.get(0).getName());
				return "M00001";
			} else {
				model.addAttribute("error", "Password Incorrect");
				return "LGN001";
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/home")
	public String home() {

		return "M00001";
	}

	@GetMapping("/userManagement")
	public ModelAndView userManagement(@ModelAttribute("success") String success, ModelMap model) {
		model.addAttribute("success", success);
		return new ModelAndView("USR001", "bean", new SearchBean());
	}

	@GetMapping("/userSearch")
	public String userSearch(@ModelAttribute("bean") SearchBean bean, ModelMap model) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setId(bean.getUserId());
		dto.setName(bean.getUserName());
		List<UserResponseDTO> list = dao.selectUser(dto);
		if (list.size() == 0) {
			model.addAttribute("error", "No user found!");
		}
		model.addAttribute("ulist", list);
		return "USR001";
	}

	@GetMapping("/userAdd")
	public ModelAndView userSetup() {
		return new ModelAndView("USR002", "bean", new UserBean());
	}

	@PostMapping("/userAdd")
	public String userAdd(@ModelAttribute("bean") @Validated UserBean bean, BindingResult br, ModelMap model) {
		if (br.hasErrors()) {
			return "USR002";
		}
		UserRequestDTO dto = new UserRequestDTO();
		if (bean.getPassword().equals(bean.getConPwd())) {
			dto.setId(bean.getId());
			

			List<UserResponseDTO> list = dao.selectUser(dto);
			if (list.size() != 0) {
				model.addAttribute("error", "User ID already exist!");
				return "USR002";
			} else {
				dto.setName(bean.getName());
				dto.setPassword(bean.getPassword());
				int i = dao.insertUser(dto);
				if (i > 0) {
					model.addAttribute("success", "User Successfully registered");
					return "USR002";
				} else {
					model.addAttribute("error", "User register fail!");
					return "USR002";
				}
			}
		} else {
			model.addAttribute("error", "Password not match!");
			return "USR002";
		}
	}

	@GetMapping("/userUpdate")
	public ModelAndView userUpdateSetup(@RequestParam("id") String id) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setId(id);
		List<UserResponseDTO> list = dao.selectUser(dto);
		UserBean bean = new UserBean();
		for (UserResponseDTO res : list) {
			bean.setId(res.getId());
			bean.setName(res.getName());
			bean.setPassword(res.getPassword());
			bean.setConPwd(res.getPassword());
		}
		return new ModelAndView("USR002-01", "bean", bean);
	}

	@PostMapping("/userUpdate")
	public String userUpdate(@ModelAttribute("bean") @Validated UserBean bean, BindingResult br, ModelMap model) {
		if (br.hasErrors()) {
			return "USR002-01";
		}

		UserRequestDTO dto = new UserRequestDTO();
		if (bean.getPassword().equals(bean.getConPwd())) {
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			dto.setPassword(bean.getPassword());
			int i = dao.updateUser(dto);
			if (i == 0) {
				model.addAttribute("error", "User update Fail");
				return "USR002-01";
			}
			model.addAttribute("success", "User successfully update");
		} else {
			model.addAttribute("error", "Password not match");
			return "USR002-01";
		}
		return "USR002-01";
	}

	@GetMapping("/userDelete")
	public String userDelete(@RequestParam("id") String id, ModelMap model, RedirectAttributes ra,
			HttpSession session) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setId(id);
		if (dto.getId().equals(session.getAttribute("userId"))) {
			ra.addAttribute("success", "Can't delet logined user!");
		} else {
			int i = dao.deleteUser(dto);
			if (i == 0) {
				model.addAttribute("error", "User Delete Fail!");
			}
			ra.addAttribute("success", "Delete successful");
		}
		return "redirect:/userManagement";
	}

}
