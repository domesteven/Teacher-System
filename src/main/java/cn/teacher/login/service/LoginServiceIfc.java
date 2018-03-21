package cn.teacher.login.service;

import cn.bean.Teacher;



public interface LoginServiceIfc {

	Teacher check(Teacher teacher);

	Boolean register(Teacher teacher);
	
}
