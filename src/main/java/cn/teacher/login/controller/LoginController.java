package cn.teacher.login.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.bean.Teacher;
import cn.tdog.po.Mytable;
import cn.tdog.po.User;
import cn.tdog.service.MytableService;
import cn.tdog.utils.AES;
import cn.tdog.utils.ExcelUtil;
import cn.tdog.utils.FormatUtil;
import cn.tdog.utils.HttpJsonUtil;
import cn.tdog.utils.HttpRequest;
import cn.teacher.login.service.LoginServiceIfc;
import cn.teacher.login.service.impl.LoginServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController{
	@Autowired
	private LoginServiceIfc loginService;
	
	private static  final transient Logger log = Logger.getLogger(LoginController .class);
	
	@Resource
	private Map<String, String> myconfig;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public ModelAndView login(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","Login请求失败");
		modelAndView.setViewName("sign-in");
		try {
			if(bindingResult.hasErrors()){
			}
			String username = teacher.gettName();
			String password = teacher.getPassword();
			Teacher bean = check(teacher);
			if(bean!=null){
				resResult.put("data", teacher);
				HttpSession session=req.getSession();
				session.setAttribute("userinfo", bean);
				modelAndView.setViewName("index");
				log.info("loginSuccess-->");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("login-->"+"errormsg:"+e.getMessage());
		}
		modelAndView.addObject("data",resResult);
		return modelAndView;
	}
	
	@RequestMapping(value="/login1")
	@ResponseBody
	public Map login1(HttpServletRequest request,HttpServletResponse response) throws Exception {
			
			Map data = HttpJsonUtil.getHttpJson(request);
			
			String username = (String) data.get("username");
	        String password = (String) data.get("password");  
	        request.getSession().setAttribute("username", username);
	        Teacher teacher = new Teacher();
	        teacher.settName(username);
	        teacher.setPassword(password);
	        
	        Map result = new HashMap();
	        result.put("userinfo", teacher);
	        JSONObject res = HttpJsonUtil.map2Json(result);
	        
	        response.addCookie(new Cookie("data", res.toString()));
			return data;
		
	}
	
	@RequestMapping(value="/loginOut")
	@ResponseBody
	public ModelAndView loginOut(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","loginOut请求失败");
		modelAndView.setViewName("sign-in");
		try {
			if(bindingResult.hasErrors()){
			}
			HttpSession session=req.getSession();
			session.invalidate();
			modelAndView.setViewName("sign-in");
		} catch (Exception e) {
			// TODO: handle exception
			log.info("loginOut-->"+"errormsg:"+e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/register")
	@ResponseBody
	public ModelAndView register(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","register请求失败");
		modelAndView.setViewName("sign-up");
		try {
			if(bindingResult.hasErrors()){
			}
			if(teacher != null){
				teacher.setAuthorlever(2);
				Boolean bar = loginService.register(teacher);
				if(bar){
					modelAndView.setViewName("sign-in");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("register-->"+"errormsg:"+e.getMessage());
		}
		return modelAndView;
	}
	@RequestMapping(value="/goLoginPage")
	@ResponseBody
	public ModelAndView goLoginPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sign-in");
		return modelAndView;
	}
	
	@RequestMapping(value="/goUserPage")
	@ResponseBody
	public ModelAndView goUserPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		return modelAndView;
	}
	
	@RequestMapping(value="/goSignInPage")
	@ResponseBody
	public ModelAndView goSignInPage(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sign-in");
		return modelAndView;
	}
	
	@RequestMapping(value="/goRegisterPage")
	@ResponseBody
	public ModelAndView goRegisterPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sign-up");
		return modelAndView;
	}
	
	@RequestMapping(value="/goPersonInfoPage")
	@ResponseBody
	public ModelAndView goPersonInfoPage(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("premium-profile");
		return modelAndView;
	}
	
	public  Teacher check(Teacher teacher){
		Teacher userinfo =  loginService.check(teacher);
		if( userinfo != null){
			return userinfo;
		}
		return null;
	}
	
	@RequestMapping(value="/getUserName")
	@ResponseBody
	public Map getUserName(HttpServletRequest req,HttpServletResponse res) throws Exception {
		Map res1 = new HashMap();
		Map data = new HashMap();
		data.put("name","ye.qin");
		data.put("gender", "female");
		res1.put("data", data);
		return res1;
	}
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse res) throws Exception {

			String path = myconfig.get("pic_download");   //request.getSession().getServletContext().getRealPath("upload");  
	        String fileName = file.getOriginalFilename();    
	        File dir = new File(path,fileName);          
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        //MultipartFile自带的解析方法  
	        file.transferTo(dir);  
	        return "ok!";  
	}
	
	@RequestMapping(value="/down")
	@ResponseBody
	public void down(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//模拟文件，myfile.txt为需要下载的文件  
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/密码.txt";  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = "下载文件.txt";  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
	}
}

