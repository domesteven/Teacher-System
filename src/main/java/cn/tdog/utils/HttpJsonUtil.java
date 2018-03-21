package cn.tdog.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpJsonUtil {

	/**
	 * 接收request中的json数据
	 * @param request
	 * @return map
	 */
	public static Map getHttpJson(HttpServletRequest request) {
		InputStream inputStream;
		String encoding;
		String body ;
		Map map = null ;
		
		try {
			inputStream = request.getInputStream();
			encoding= request.getCharacterEncoding(); 
			body = IOUtils.toString(inputStream, encoding); 
			map = parseJSON2Map(body);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * json -> map
	 * @param jsonStr
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr){      
        Map<String, Object> map = new HashMap<String, Object>();      
        JSONObject json = JSONObject.fromObject(jsonStr);      
        for(Object k : json.keySet()){      
            Object v = json.get(k);       
            if(v instanceof JSONArray){      
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();      
                Iterator<JSONObject> it = ((JSONArray)v).iterator();      
                while(it.hasNext()){      
                    JSONObject json2 = it.next();      
                    list.add(parseJSON2Map(json2.toString()));      
                }      
                map.put(k.toString(), list);      
            } else {      
                map.put(k.toString(), v);      
            }      
        }      
        return map;      
    }    
	
	/**
	 *map -> json 
	 */
	public static JSONObject map2Json(Map<String,Object> map){  
        JSONObject json = new JSONObject();  
        Set<String> set = map.keySet();  
        for (Iterator<String> it = set.iterator();it.hasNext();) {  
            String key = it.next();  
            json.put(key, map.get(key));  
        }         
        return json;  
    }  
}
