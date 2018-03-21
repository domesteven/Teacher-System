package cn.tdog.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.junit.Test;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub  
        Properties properties = new Properties();  
        InputStream inputStream = null;  
        try {  
            //加载配置文件  
            inputStream = getClass().getResourceAsStream("/myconfig.properties");  
            properties.load(inputStream);  
            
            Enumeration e = properties.propertyNames(); 
            while (e.hasMoreElements()) {  
                String key = (String) e.nextElement();  
                String value = (String) properties.get(key);  
                arg0.getSession().setAttribute(key, value);  
            }  
             //解析配置文件，其中production_url为配置文件中一个参数的key  
             //String url = (String) properties.get("pic_download");  
             //下面是你拿到参数的使用，这个看需要，这里我把参数值放到了session中  
             //	arg0.getSession().setAttribute("pic_download", url);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  

	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
}
