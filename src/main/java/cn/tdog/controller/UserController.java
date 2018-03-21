package cn.tdog.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tdog.po.Mytable;
import cn.tdog.po.User;
import cn.tdog.service.MytableService;
import cn.tdog.utils.AES;
import cn.tdog.utils.ExcelUtil;
import cn.tdog.utils.FormatUtil;
import cn.tdog.utils.HttpJsonUtil;
import cn.tdog.utils.HttpRequest;
import cn.tdog.utils.JsonUtils;




@Controller
public class UserController{
	
	@Autowired
	private MytableService mytableService;
	
	@Resource
	private Map<String, String> user;
	
	@RequestMapping(value="/getUser")
	@ResponseBody
	public void getUser(HttpServletRequest req,HttpServletResponse res) throws Exception {
		
		System.out.println(user);
		User user = new User();
		user.setId(1);
		user.setName("zhangsan");
		user.setAge(15);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("user");
//		ModelAndView modelAndView = new ModelAndView();
//		List<Mytable> list=mytableService.findUserMytable();
//		modelAndView.addObject("list",list);
//		modelAndView.setViewName("user");
		
		List list = new ArrayList();
		list.add(user);
		//返回json
		HashMap<String,List> map=new HashMap<String,List>();
		map.put("res",list);
		JSONObject json = JSONObject.fromObject(map);
		req.setAttribute("json", json);
		req.getRequestDispatcher("jsonReq.jsp").forward(req,res);
	}
	
	@RequestMapping(value="/setUser")
	public ModelAndView setUser(@Validated Mytable mytable,BindingResult bindingResult,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) throws Exception {
		String msg = "";
		String path= "success";
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()){
			List<ObjectError> allerrors = bindingResult.getAllErrors();
			for(ObjectError error : allerrors){
				System.out.println(error.getDefaultMessage());
			}
			modelAndView.addObject("allerrors",allerrors);
			modelAndView.setViewName("login");
			return modelAndView;
		}
		try{
			int a=mytableService.setUser(mytable);
			msg = "添加成功！";
			
		}catch (Exception e) {
			// TODO: handle exception
			msg= "添加失败！";
			System.out.println(e.getMessage());
			
		}
		 
		if(!file.isEmpty()  && file.getOriginalFilename() != null ){
			String oriFileName = file.getOriginalFilename();
			String pic_download = (String) request.getSession().getAttribute("pic_download");
			String phone = (String) request.getSession().getAttribute("phone");
			String pic_path = pic_download;
			
			//新的图片名称
			String newFileName = UUID.randomUUID()+oriFileName.substring(oriFileName.lastIndexOf("."));
			
			//新图片
			File newFile = new File(pic_path+newFileName);
			
			file.transferTo(newFile);
			
		}
		
		modelAndView.addObject("msg",msg);
		modelAndView.setViewName(path);
		return modelAndView;
	}
	
	@RequestMapping(value="/reqJson")
	@ResponseBody
	public  Map reqJson() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		ArrayList lista = new ArrayList();
		ArrayList listb = new ArrayList();
		map2.put("id", 1);
		map2.put("merchant_id", 11065);
		map2.put("edit_time", "2017-11-07 18:18:58");
		map2.put("wx_applet_name", "EV课堂小助手");
		map2.put("wx_applet_logo", "http://img1.3lian.com/2015/w10/50/d/63.jpg");
		map2.put("wx_applet_remark", "EV课堂小助手是一款帮助中小企业及个人教师，快速将原创付费课程变现的工具。");
		map2.put("is_recomm", 1);

		lista.add(map2);
		map1.put("recommendList", lista);
		map1.put("historyList", listb);
		
		map.put("errcode",0);
		map.put("errmsg","请求推荐商户信息返回");
		map.put("result",map1);
		
		return map;
	}
	
	@RequestMapping(value="/getWxAes")
	@ResponseBody
	public  Map getWxAes(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map data = HttpJsonUtil.getHttpJson(request);
		
		String encryptedData = data.get("encryptedData").toString();  
          
        String iv = data.get("iv").toString();  
  
        String sessionKey = data.get("sessionKey").toString();  
        System.out.println("sessionKey:" + sessionKey);  
          
        @SuppressWarnings("rawtypes")  
        Map map = new HashMap();  
        try {  
                byte[] resultByte  = AES.decrypt(Base64.decode(encryptedData),  
                        Base64.decode(sessionKey),Base64.decode(iv));  
                    if(null != resultByte && resultByte.length > 0){  
                        String result = new String(resultByte, "UTF-8"); 
                        map.put("status", 1);  
                        map.put("msg", "解密成功");                 
                        map.put("data", result);  
                    }else{  
                        map.put("status", 0);  
                        map.put("msg", "解密失败");  
                    }  
            }catch (InvalidAlgorithmParameterException e) {  
                    e.printStackTrace();  
            } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
            }                
        JSONArray json_test = JSONObject.fromObject(map.get("data")).getJSONArray("stepInfoList");
        ObjectMapper mapper = new ObjectMapper();
        List<Map> conList = mapper.readValue(json_test.toString(), List.class);
        
        System.out.println(conList);
        for(int i = 0; i<conList.size();i++){
        	//FormatUtil.format(conList.get(i).get("timestamp"));
        	System.out.println(conList.get(i).get("timestamp"));
        	String timestamp =""+conList.get(i).get("timestamp");
        	String time = FormatUtil.timeStamp2Date(timestamp);
        	conList.get(i).put("timestamp", time);
        }
        Collections.reverse(conList); // 倒序排列 
        map.put("data",conList);
        System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="/getSessionKey")
	@ResponseBody
	public  String getSessionKey(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map data = HttpJsonUtil.getHttpJson(request);
		
		String code = (String) data.get("js_code");
        //appid微信公众号平台获得  
        String appId = (String) data.get("appid");  
        //appserect,微信公众号平台获得  
        String appSecret =(String) data.get("secret");  
        //获取code  
        String url = "https://api.weixin.qq.com/sns/jscode2session";  
        String param = "appid="+ appId +"&secret=" + appSecret   
                + "&js_code=" + code +"&grant_type=authorization_code";  

        String result = HttpRequest.sendGet(url, param);  
        System.out.println(result);
		return result;
	}
	
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public int updateUser()throws Exception{
		Mytable mytable = new Mytable();
		int a=mytableService.updateUser(mytable);
		return a;
		
	}
	
	//Excel表格下载
	@RequestMapping(value="/download")
	public String download(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fileName="excelFile";
        //填充Mytables数据
        List<Mytable> mytables=createData();
        List<Map<String,Object>> list=createExcelRecord(mytables);
        String columnNames[]={"ID","名字","年龄"};//列名
        String keys[]   =    {"id","name","age"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }
	
	//数据源
	private List<Mytable> createData() throws Exception {
        // TODO Auto-generated method stub
        //自己实现
		
		List<Mytable> list=mytableService.findUserMytable();
        return list;
    }
	private List<Map<String, Object>> createExcelRecord(List<Mytable> Mytables) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Mytable mytable=null;
        for (int j = 0; j < Mytables.size(); j++) {
        	mytable=Mytables.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", mytable.getId());
            mapValue.put("name", mytable.getName());
            mapValue.put("age", mytable.getAge());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
