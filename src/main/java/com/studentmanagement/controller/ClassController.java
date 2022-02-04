package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studentmanagement.dao.ClassDAO;
import com.studentmanagement.dto.ClassRequestDTO;
import com.studentmanagement.dto.ClassResponseDTO;
import com.studentmanagement.model.ClassBean;

@Controller
public class ClassController {

	@Autowired
	private ClassDAO dao;

	@GetMapping("/classRegister")
	public ModelAndView classRegisterSetup() {
		return new ModelAndView("BUD003", "bean", new ClassBean());
	}

	@PostMapping("/classRegister")
	public String classRegister(@ModelAttribute("bean") @Validated ClassBean bean, BindingResult br, ModelMap model) {
		if (br.hasErrors()) {
			return "BUD003";
		}

		ClassRequestDTO dto = new ClassRequestDTO();
		dto.setId(bean.getId());

		List<ClassResponseDTO> list = dao.selectClass(dto);
		if (list.size() != 0) {
			model.addAttribute("error", "ClassID already exist!");
			return "BUD003";
		} else {
			dto.setName(bean.getName());
			int i = dao.insertClass(dto);
			if (i > 0) {
				model.addAttribute("success", "Class Successfully Registered");
				return "BUD003";
			} else {
				model.addAttribute("error", "Class Register Fail!");
				return "BUD003";
			}
		}
	}
}
