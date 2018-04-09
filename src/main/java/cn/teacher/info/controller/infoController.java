package cn.teacher.info.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bean.Teacher;

@Controller
public class infoController {
	private static  final transient Logger log = Logger.getLogger(infoController.class);
	
	@RequestMapping(value="/goShowInfoPage")
	@ResponseBody
	public ModelAndView goShowInfoPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("showInfo");
		return modelAndView;
	}
	@RequestMapping(value="/goEditInfoPage")
	@ResponseBody	
	public ModelAndView goEditInfoPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editInfo");
		return modelAndView;
	}
}
