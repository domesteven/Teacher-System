package cn.tdog.genreator;
import java.awt.geom.GeneralPath;
import java.awt.im.InputContext;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.chainsaw.Main;
import org.apache.log4j.lf5.util.Resource;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MyGen {
	
	public static void main (String[] args) throws Exception  {
		try {
	    	 MyGen mygen = new MyGen();
	    	 mygen.generator();
	     } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	}
	public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        
        File configFile = new File("D:/workspace4/WebTemplate/src/main/resources/genreatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
	}
}
