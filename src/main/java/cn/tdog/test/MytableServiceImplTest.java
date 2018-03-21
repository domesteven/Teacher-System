package cn.tdog.test;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.tdog.mapper.MytableMapper;
import cn.tdog.po.Mytable;

public class MytableServiceImplTest {
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception{
//		String resource = "mybatis/sqlMapConfig.xml";
		String resource = "mybatis/sqlMapConfig.xml"; //mybatis配置文件 
        //得到配置文件的流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      
	}
	@Test
	public void testFindUserMytable() {
		try{
			SqlSession sqlSession = sqlSessionFactory.openSession();
			MytableMapper mytableMapper = sqlSession.getMapper(MytableMapper.class);
			//List<Mytable> list = mytableMapper.selectAll();
			sqlSession.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testSetUser() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MytableMapper mytableMapper = sqlSession.getMapper(MytableMapper.class);
		
		Mytable mytable = new Mytable();
		mytable.setName("叶凡");
		mytable.setAge(new BigDecimal(88));
		mytable.setId(57);
		mytableMapper.updateByExample(mytable,null);
		sqlSession.close();
	}
}
