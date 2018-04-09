package cn.teacher.info.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bean.Teacher;
import cn.mapper.TeacherMapper;
import cn.teacher.info.service.infoServiceIfc;

public class infoServiceImpl implements infoServiceIfc {
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public void updateInfo(Teacher teacher) {
		// TODO Auto-generated method stub
		try {
			if(teacher!=null){
				teacherMapper.updateByPrimaryKey(teacher);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@Override
	public void insertInfo(Teacher teacher) {
		// TODO Auto-generated method stub
		try {
			if(teacher!=null){
				teacherMapper.insert(teacher);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
