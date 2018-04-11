package cn.teacher.info.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import cn.bean.TaskTeaching;
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
	@RequestMapping(value="/delTaskTeaching")
	@ResponseBody	
	public Map delTaskTeaching(@Validated TaskTeaching taskTeaching,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		data.put("errcode", "-1");
		data.put("errmsg", "失败");
		try {
			infoService.delTaskTeaching(taskTeaching);
			data.put("errcode", "0");
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return data; 
	}
	
	@RequestMapping(value="/saveTaskTeaching")	
	public String saveTaskTeaching(@Validated TaskTeaching taskTeaching,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		
		try {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			taskTeaching.setCode(uuid);
			infoService.insert(taskTeaching);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "redirect:/goTeachingTask"; 
	}
	@RequestMapping(value="/goTeachingTask")
	@ResponseBody	
	public ModelAndView goTeachingTask(@Validated TaskTeaching bean,String page,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("teachingTask");
		try {
			String parameter = req.getParameter("name");
			if(StringUtils.isNotBlank(parameter)){  
	            String keyword=java.net.URLDecoder.decode(parameter,"UTF-8");  
	            bean.setName(keyword);
	            modelAndView.addObject("searchName", keyword);
	        }  
			Teacher teacher = (Teacher) req.getSession().getAttribute("userinfo");
			int tId = teacher.gettId();
			bean.settId(tId);
			//每页显示条数
			int pageSize = Integer.parseInt(myconfig.get("pageSize"));
			
			List<TaskTeaching> list = infoService.selectAllTaskTeaching(bean);
			modelAndView.addObject("userNum",list.size());
			
			//总页数
			int pageTimes;
			if(list.size()>pageSize){
				if(list.size() % pageSize == 0){
					pageTimes = list.size()/pageSize;
				}else{
					pageTimes = list.size()/pageSize+1;
				}
			}else{
				pageTimes = 1;
			}
			
			
			modelAndView.addObject("pageTimes", pageTimes);
			//页面初始的时候page没有值
	         if(null == page)
	         {
	             page = "1";
	         }
	         
	         int startRow = (Integer.parseInt(page)-1) * pageSize;
	         list = infoService.selectTaskTeachingByPage(bean,startRow, pageSize);
	         
	         modelAndView.addObject("currentPage", Integer.parseInt(page));
	         modelAndView.addObject("list", list);
	         
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		
		return modelAndView;
	}
	
}
