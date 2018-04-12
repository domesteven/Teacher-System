package cn.teacher.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bean.TaskTeaching;
import cn.bean.TaskTeachingExample;
import cn.bean.Teacher;
import cn.bean.TeacherExample;
import cn.mapper.TaskTeachingMapper;
import cn.mapper.TeacherMapper;
import cn.teacher.info.service.infoServiceIfc;

public class infoServiceImpl implements infoServiceIfc {
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private TaskTeachingMapper taskTeachingMapper;
	
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

	@Override
	public List<TaskTeaching> selectAllTaskTeaching(TaskTeaching bean) {
		List<TaskTeaching> list = null;
		try {
			TaskTeachingExample example = new TaskTeachingExample();
			TaskTeachingExample.Criteria criteria = example.createCriteria();
			criteria.andTIdEqualTo(bean.gettId());
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			list = taskTeachingMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<TaskTeaching> selectTaskTeachingByPage(TaskTeaching bean,int startRow,
			int pageSize) {
		// TODO Auto-generated method stub
		List<TaskTeaching> list = null;
		try {
			TaskTeachingExample example = new TaskTeachingExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TaskTeachingExample.Criteria criteria = example.createCriteria();
			criteria.andTIdEqualTo(bean.gettId());
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			list = taskTeachingMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public void delTaskTeaching(TaskTeaching taskTeaching) {
		// TODO Auto-generated method stub
		try {
			taskTeachingMapper.deleteByPrimaryKey(taskTeaching.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void insert(TaskTeaching taskTeaching) {
		// TODO Auto-generated method stub
		taskTeachingMapper.insert(taskTeaching);
	}

	@Override
	public TaskTeaching selectTaskTeachingById(TaskTeaching taskTeaching) {
		// TODO Auto-generated method stub
		TaskTeaching bean = null;
		try {
			bean = taskTeachingMapper.selectByPrimaryKey(taskTeaching.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void update(TaskTeaching taskTeaching) {
		// TODO Auto-generated method stub
		taskTeachingMapper.updateByPrimaryKey(taskTeaching);
	}

}
