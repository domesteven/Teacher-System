package cn.tdog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.bean.Teacher;
import cn.teacher.login.controller.LoginController;
import cn.teacher.login.service.LoginServiceIfc;

public class HandInterceptor1 implements HandlerInterceptor {
	
	private static  final transient Logger log = Logger.getLogger(HandInterceptor1 .class);
	
	@Autowired
	private LoginController LoginController;
	
	public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除   
	
	public String[] adminUrls;//权限认证拦截
	
	public void setAllowUrls(String[] allowUrls) {    
        this.allowUrls = allowUrls;    
    }   
	
	public void setAdminUrls(String[] adminUrls) {    
        this.adminUrls = adminUrls;    
    }   
	//进入handler之前执行
	//用于身份认证、身份授权
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		request.setCharacterEncoding("UTF8");  
        HttpSession session=request.getSession();//获取登录的SESSION   
         
        String sessionid=request.getSession().getId();//获取登录的SESSIONID  
        String requestPath=request.getServletPath();//获取客户请求页面  
        System.out.println("=============="+requestPath);
        Teacher teacher = (Teacher) session.getAttribute("userinfo");//获取登录的用户 
 
        
      //先过滤掉不需要判断SESSION的请求  
        for(String url : allowUrls) {     
            if(requestPath.contains(url)) {      
                return true;      
            }  
        }
      //判断用户名是否有效
        Teacher userinfo = LoginController.check(teacher);
        if(userinfo!=null){ 
        	for(String url : adminUrls) {     
                if(requestPath.contains(url)) {  
                	if(userinfo.getAuthorlever()== 2){
                		log.info("权限不够");
                		response.sendRedirect("sign-in.jsp");
                		return false; 
                	}    
                }  
            }
            return true;   
         }else{  
             log.info("不允许登录");  
             session.invalidate();  
             response.sendRedirect("sign-in.jsp");
             return false;  
         }  
	}
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=============="+"postHandle");

	}
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=============="+"afterCompletion");
	}
}
