package cn.teacher.info.controller;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.bean.Teacher;
import cn.teacher.info.service.infoServiceIfc;

@Controller
public class infoController {
	private static  final transient Logger log = Logger.getLogger(infoController.class);
	
	@Autowired
	private infoServiceIfc infoService;
	
	@Resource
	private Map<String, String> myconfig;
	
	@RequestMapping(value="/saveInfo")
	@ResponseBody
	public ModelAndView saveInfo(@RequestParam(value = "file", required = false) MultipartFile file,@Validated Teacher teacher,HttpServletRequest request,HttpServletResponse res) throws Exception {
		String fileName = null;
		try {
			if(!file.isEmpty()){
				String path = myconfig.get("pic_download");   //request.getSession().getServletContext().getRealPath("upload");  
				fileName = file.getOriginalFilename();    
		        File dir = new File(path,fileName);          
		        if(!dir.exists()){  
		            dir.mkdirs();  
		        }  
		        //MultipartFile自带的解析方法  
		        file.transferTo(dir);  
				teacher.setImage(fileName);
			}else{
				teacher.setImage(((Teacher) request.getSession().getAttribute("userinfo")).getImage());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		
		
		teacher.setAuthorlever(((Teacher) request.getSession().getAttribute("userinfo")).getAuthorlever());
		if(teacher.gettId()!=null){
			infoService.updateInfo(teacher);
		}else{
			infoService.insertInfo(teacher);
		}
		request.getSession().setAttribute("userinfo", teacher);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("showInfo");
		return modelAndView;
	}
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
	@RequestMapping(value="/goUploadPage")
	@ResponseBody	
	public ModelAndView goUploadPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload");
		return modelAndView;
	}
}
