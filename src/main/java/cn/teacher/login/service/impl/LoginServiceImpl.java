package cn.teacher.login.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;




import cn.bean.Teacher;
import cn.bean.TeacherExample;
import cn.mapper.TeacherMapper;
import cn.tdog.mapper.MytableMapper;
import cn.teacher.login.service.LoginServiceIfc;

public class LoginServiceImpl implements LoginServiceIfc {
	@Autowired
	private TeacherMapper teacherMapper;
	
	public Teacher check(Teacher teacher) {
		// TODO Auto-generated method stub
		if(teacher != null){
			String username = teacher.gettName();
			String password = teacher.getPassword();
			TeacherExample bean = new TeacherExample();
			TeacherExample.Criteria criteria = bean.createCriteria();
			criteria.andTNameEqualTo(username);
			criteria.andPasswordEqualTo(password);
			List<Teacher> teachers = teacherMapper.selectByExample(bean);
			if(teachers.size()!=0){
				return teachers.get(0);
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
