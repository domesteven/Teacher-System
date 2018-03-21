package cn.teacher.login.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;


import cn.bean.Teacher;
import cn.mapper.TeacherMapper;
import cn.tdog.mapper.MytableMapper;
import cn.teacher.login.service.LoginServiceIfc;

public class LoginServiceImpl implements LoginServiceIfc {
	@Autowired
	private TeacherMapper teacherMapper;
	
	public Teacher check(Teacher teacher) {
		// TODO Auto-generated method stub
		if(teacher != null){
			String username = teacher.getUsername();
			String password = teacher.getPassword();
			Teacher bean = teacherMapper.selectByUserName(username);
			if(bean.getPassword().equals(password)){
				return bean;
			}
		}
		return null;
	}

	public Boolean register(Teacher teacher) {
		Boolean res = false;
		if(teacher != null){
			int num = teacherMapper.insert(teacher);
			if(num > 0){
				res = true;
			}
		}
		return res;
	}

	
	

}
