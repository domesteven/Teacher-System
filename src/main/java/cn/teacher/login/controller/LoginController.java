package cn.teacher.login.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import cn.teacher.info.service.infoServiceIfc;
import cn.teacher.login.service.LoginServiceIfc;
import cn.teacher.login.service.impl.LoginServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController{
	@Autowired
	private LoginServiceIfc loginService;
	
	@Autowired
	private infoServiceIfc infoService;
	
	private static  final transient Logger log = Logger.getLogger(LoginController .class);
	
	@Resource
	private Map<String, String> myconfig;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Map login(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","登录验证失败");
		
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
				if(bean.getAuthorlever() == 1 && session.getAttribute("openAuthor") == null){
					session.setAttribute("openAuthor", "false");
				}
				resResult.put("errcode","0");
				resResult.put("errmsg","登录成功");
				log.info("loginSuccess-->");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("login-->"+"errormsg:"+e.getMessage());
		}
		
		return resResult;
	}
	
	
	@RequestMapping(value="/editPwd")
	@ResponseBody
	public Map editPwd(@Validated Teacher teacher,String password1,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","修改密码失败");
		
		try {
			HttpSession session=req.getSession();
			Teacher userinfo = (Teacher) session.getAttribute("userinfo");
			teacher.settName(userinfo.gettName());
			Teacher currentUser = check(teacher);
			if(currentUser != null){
				currentUser.setPassword(password1);
				infoService.update(currentUser);
				resResult.put("errcode", "0");
				resResult.put("errmsg", "修改密码成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("login-->"+"errormsg:"+e.getMessage());
		}
		
		return resResult;
	}
	
	
	@RequestMapping(value="/goIndex")
	@ResponseBody
	public ModelAndView goIndex(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("showInfo");
		return modelAndView;
	}
	
	@RequestMapping(value="/goEditPwd")
	@ResponseBody
	public ModelAndView goEditPwd(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editPwd");
		return modelAndView;
	}
	
	@RequestMapping(value="/goRegist")
	@ResponseBody
	public ModelAndView goRegist(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist");
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
		String name = (String) req.getAttribute("tName");
		ModelAndView modelAndView = new ModelAndView();
		Map resResult = new HashMap();
		Map data = new HashMap();
		resResult.put("errcode","-1");
		resResult.put("errmsg","register请求失败");
		modelAndView.setViewName("regist");
		try {
			if(bindingResult.hasErrors()){
			}
			if(teacher != null){
				Boolean hasName = loginService.checkNameAndNumber(teacher);
				if(hasName){
					teacher.setAuthorlever(2);
					Boolean bar = loginService.register(teacher);
					if(bar){
						modelAndView.setViewName("sign-in");
					}
				}else{
					modelAndView.addObject("error",resResult);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("register-->"+"errormsg:"+e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/changeAuthor")
	@ResponseBody
	public Map changeAuthor(HttpServletRequest req,HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		String openAuthor = (String) req.getSession().getAttribute("openAuthor");
		if(openAuthor != null){
			if(openAuthor == "true"){
				req.getSession().setAttribute("openAuthor", "false");
			}else{
				req.getSession().setAttribute("openAuthor", "true");
			}
			data.put("errcode", "0");
			data.put("msg", "切换成功");
		}
		return data;
	}
	@RequestMapping(value="/checkNum")
	@ResponseBody
	public Map checkNum(@Validated Teacher teacher,BindingResult bindingResult,HttpServletRequest req,HttpServletResponse res) throws Exception {
		Map data = new HashMap();
		Boolean hasName = loginService.checkNameAndNumber(teacher);
		if(!hasName){
			data.put("errcode", "-1");
			data.put("errmsg", "身份证号码重复");
		}else{
			data.put("errcode", "0");
		}
		return data;
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
	public HttpServletResponse down(String attach,HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			
			String path = myconfig.get("pic_download")+ URLDecoder.decode(attach,"UTF-8");
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String userAgent = request.getHeader("User-Agent");  
            byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes()  
                    : filename.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题  
            filename = new String(bytes, "ISO-8859-1");
            
            //response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            response.setHeader("Content-disposition",  String.format("attachment; filename=\"%s\"", filename));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }
}

