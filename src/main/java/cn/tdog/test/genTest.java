package cn.tdog.test;

import org.junit.Test;

import cn.tdog.genreator.MyGen;

public class genTest {
	
	@Test
	public void test1() throws Exception{
		MyGen mygen = new MyGen();
		mygen.generator();
	}
}
