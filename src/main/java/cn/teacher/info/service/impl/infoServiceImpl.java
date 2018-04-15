package cn.teacher.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bean.TaskCompany;
import cn.bean.TaskCompanyExample;
import cn.bean.TaskDirectortournament;
import cn.bean.TaskDirectortournamentExample;
import cn.bean.TaskGraduation;
import cn.bean.TaskGraduationExample;
import cn.bean.TaskTeaching;
import cn.bean.TaskTeachingExample;
import cn.bean.TaskTutor;
import cn.bean.TaskTutorExample;
import cn.bean.Teacher;
import cn.bean.TeacherExample;
import cn.mapper.TaskCompanyMapper;
import cn.mapper.TaskDirectortournamentMapper;
import cn.mapper.TaskGraduationMapper;
import cn.mapper.TaskTeachingMapper;
import cn.mapper.TaskTutorMapper;
import cn.mapper.TeacherMapper;
import cn.teacher.info.service.infoServiceIfc;

public class infoServiceImpl implements infoServiceIfc {
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private TaskTeachingMapper taskTeachingMapper;
	
	@Autowired
	private TaskCompanyMapper taskCompanyMapper;
	
	@Autowired
	private TaskDirectortournamentMapper taskDirectortournamentMapper;
	
	@Autowired
	private TaskGraduationMapper taskGraduationMapper;
	
	@Autowired
	private TaskTutorMapper taskTutorMapper;
	
	
	@Override
	public List<Teacher> selectAllTeacher() {
		// TODO Auto-generated method stub
		List<Teacher> list = null;
		try {
			TeacherExample example = new TeacherExample();
			TeacherExample.Criteria criteria = example.createCriteria();
			list = teacherMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
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
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
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
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
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

	@Override
	public List<TaskCompany> selectAllTaskCompany(TaskCompany bean) {
		List<TaskCompany> list = null;
		try {
			TaskCompanyExample example = new TaskCompanyExample();
			TaskCompanyExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskCompanyMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<TaskCompany> selectTaskCompanyByPage(TaskCompany bean,
			int startRow, int pageSize) {
		List<TaskCompany> list = null;
		try {
			TaskCompanyExample example = new TaskCompanyExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TaskCompanyExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskCompanyMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public void delTaskCompany(TaskCompany taskCompany) {
		// TODO Auto-generated method stub
		try {
			taskCompanyMapper.deleteByPrimaryKey(taskCompany.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void insert(TaskCompany taskCompany) {
		// TODO Auto-generated method stub
		taskCompanyMapper.insert(taskCompany);
	}

	@Override
	public void update(TaskCompany taskCompany) {
		// TODO Auto-generated method stub
		taskCompanyMapper.updateByPrimaryKey(taskCompany);
	}

	@Override
	public TaskCompany selectTaskCompanyById(TaskCompany taskCompany) {
		// TODO Auto-generated method stub
		TaskCompany bean = null;
		try {
			bean = taskCompanyMapper.selectByPrimaryKey(taskCompany.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void insert(TaskGraduation taskBean) {
		// TODO Auto-generated method stub
		taskGraduationMapper.insert(taskBean);
	}

	@Override
	public void update(TaskGraduation taskBean) {
		// TODO Auto-generated method stub
		taskGraduationMapper.updateByPrimaryKey(taskBean);
		
	}

	@Override
	public TaskGraduation selectTaskCompanyById(TaskGraduation taskBean) {
		TaskGraduation bean = null;
		try {
			bean = taskGraduationMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public List<TaskGraduation> selectAllTaskCompany(TaskGraduation bean) {
		List<TaskGraduation> list = null;
		try {
			TaskGraduationExample example = new TaskGraduationExample();
			TaskGraduationExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskGraduationMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<TaskGraduation> selectTaskCompanyByPage(TaskGraduation bean,
			int startRow, int pageSize) {
		// TODO Auto-generated method stub
		List<TaskGraduation> list = null;
		try {
			TaskGraduationExample example = new TaskGraduationExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TaskGraduationExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskGraduationMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public void delTaskGraduation(TaskGraduation taskBean) {
		// TODO Auto-generated method stub
		try {
			taskGraduationMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<TaskDirectortournament> selectAllTask(
			TaskDirectortournament bean) {
		// TODO Auto-generated method stub
		List<TaskDirectortournament> list = null;
		try {
			TaskDirectortournamentExample example = new TaskDirectortournamentExample();
			TaskDirectortournamentExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskDirectortournamentMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<TaskDirectortournament> selectTaskByPage(
			TaskDirectortournament bean, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		List<TaskDirectortournament> list = null;
		try {
			TaskDirectortournamentExample example = new TaskDirectortournamentExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TaskDirectortournamentExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskDirectortournamentMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public void delTask(TaskDirectortournament taskBean) {
		// TODO Auto-generated method stub
		try {
			taskDirectortournamentMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void insert(TaskDirectortournament taskBean) {
		// TODO Auto-generated method stub
		taskDirectortournamentMapper.insert(taskBean);
	}

	@Override
	public void update(TaskDirectortournament taskBean) {
		// TODO Auto-generated method stub
		taskDirectortournamentMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public TaskDirectortournament selectTaskById(TaskDirectortournament taskBean) {
		TaskDirectortournament bean = null;
		try {
			bean = taskDirectortournamentMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public List<TaskTutor> selectAllTask(TaskTutor bean) {
		// TODO Auto-generated method stub
		List<TaskTutor> list = null;
		try {
			TaskTutorExample example = new TaskTutorExample();
			TaskTutorExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getStudentName()!=null && bean.getStudentName()!=""){
				criteria.andStudentNameLike("%"+bean.getStudentName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskTutorMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<TaskTutor> selectTaskByPage(TaskTutor bean, int startRow,
			int pageSize) {
		// TODO Auto-generated method stub
		List<TaskTutor> list = null;
		try {
			TaskTutorExample example = new TaskTutorExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TaskTutorExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getStudentName()!=null && bean.getStudentName()!=""){
				criteria.andStudentNameLike("%"+bean.getStudentName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = taskTutorMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public TaskTutor selectTaskById(TaskTutor taskBean) {
		// TODO Auto-generated method stub
		TaskTutor bean = null;
		try {
			bean = taskTutorMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void update(TaskTutor taskBean) {
		// TODO Auto-generated method stub
		taskTutorMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public void insert(TaskTutor taskBean) {
		// TODO Auto-generated method stub
		taskTutorMapper.insert(taskBean);
	}

	@Override
	public void delTask(TaskTutor taskBean) {
		// TODO Auto-generated method stub
		try {
			taskTutorMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

}
