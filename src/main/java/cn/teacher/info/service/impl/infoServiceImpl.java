package cn.teacher.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bean.ProjectLecture;
import cn.bean.ProjectLectureExample;
import cn.bean.ProjectPerson;
import cn.bean.ProjectPersonExample;
import cn.bean.ProjectPublish;
import cn.bean.ProjectPublishExample;
import cn.bean.ProjectSocialservice;
import cn.bean.ProjectSocialserviceExample;
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
import cn.mapper.ProjectLectureMapper;
import cn.mapper.ProjectPersonMapper;
import cn.mapper.ProjectPublishMapper;
import cn.mapper.ProjectSocialserviceMapper;
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
	
	@Autowired
	private ProjectPublishMapper projectPublishMapper;
	
	@Autowired
	private ProjectPersonMapper projectPersonMapper;
	
	@Autowired
	private ProjectSocialserviceMapper projectSocialserviceMapper;
	
	@Autowired
	private ProjectLectureMapper projectLectureMapper;
	
	
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
	
	@Override
	public List<ProjectPublish> selectAllTask(ProjectPublish bean) {
		List<ProjectPublish> list = null;
		try {
			ProjectPublishExample example = new ProjectPublishExample();
			ProjectPublishExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectPublishMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<ProjectPublish> selectTaskByPage(ProjectPublish bean,
			int startRow, int pageSize) {
		List<ProjectPublish> list = null;
		try {
			ProjectPublishExample example = new ProjectPublishExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			ProjectPublishExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectPublishMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public ProjectPublish selectTaskById(ProjectPublish taskBean) {
		// TODO Auto-generated method stub
		ProjectPublish bean = null;
				try {
					bean = projectPublishMapper.selectByPrimaryKey(taskBean.getId());
				} catch (Exception e) {
					// TODO: handle exception
				}
				return bean;
	}

	@Override
	public void update(ProjectPublish taskBean) {
		// TODO Auto-generated method stub
		projectPublishMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public void insert(ProjectPublish taskBean) {
		// TODO Auto-generated method stub
		projectPublishMapper.insert(taskBean);
	}

	@Override
	public void delTask(ProjectPublish taskBean) {
		// TODO Auto-generated method stub
		try {
			projectPublishMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<ProjectPerson> selectAllTask(ProjectPerson bean) {
		List<ProjectPerson> list = null;
		try {
			ProjectPersonExample example = new ProjectPersonExample();
			ProjectPersonExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectPersonMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<ProjectPerson> selectTaskByPage(ProjectPerson bean,
			int startRow, int pageSize) {
		List<ProjectPerson> list = null;
		try {
			ProjectPersonExample example = new ProjectPersonExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			ProjectPersonExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectPersonMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public ProjectPerson selectTaskById(ProjectPerson taskBean) {
		// TODO Auto-generated method stub
		ProjectPerson bean = null;
		try {
			bean = projectPersonMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void update(ProjectPerson taskBean) {
		// TODO Auto-generated method stub
		projectPersonMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public void delTask(ProjectPerson taskBean) {
		// TODO Auto-generated method stub
		try {
			projectPersonMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void insert(ProjectPerson taskBean) {
		// TODO Auto-generated method stub
		projectPersonMapper.insert(taskBean);
	}

	@Override
	public List<ProjectSocialservice> selectAllTask(ProjectSocialservice bean) {
		// TODO Auto-generated method stub
		List<ProjectSocialservice> list = null;
		try {
			ProjectSocialserviceExample example = new ProjectSocialserviceExample();
			ProjectSocialserviceExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectSocialserviceMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<ProjectSocialservice> selectTaskByPage(
			ProjectSocialservice bean, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		List<ProjectSocialservice> list = null;
		try {
			ProjectSocialserviceExample example = new ProjectSocialserviceExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			ProjectSocialserviceExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectSocialserviceMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public ProjectSocialservice selectTaskById(ProjectSocialservice taskBean) {
		// TODO Auto-generated method stub
		ProjectSocialservice bean = null;
		try {
			bean = projectSocialserviceMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void update(ProjectSocialservice taskBean) {
		// TODO Auto-generated method stub
		projectSocialserviceMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public void insert(ProjectSocialservice taskBean) {
		// TODO Auto-generated method stub
		projectSocialserviceMapper.insert(taskBean);
	}

	@Override
	public void delTask(ProjectSocialservice taskBean) {
		// TODO Auto-generated method stub
		try {
			projectSocialserviceMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<ProjectLecture> selectAllTask(ProjectLecture bean) {
		List<ProjectLecture> list = null;
		try {
			ProjectLectureExample example = new ProjectLectureExample();
			ProjectLectureExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectLectureMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<ProjectLecture> selectTaskByPage(ProjectLecture bean,
			int startRow, int pageSize) {
		// TODO Auto-generated method stub
		List<ProjectLecture> list = null;
		try {
			ProjectLectureExample example = new ProjectLectureExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			ProjectLectureExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getName()!=null && bean.getName()!=""){
				criteria.andNameLike("%"+bean.getName()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			list = projectLectureMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public ProjectLecture selectTaskById(ProjectLecture taskBean) {
		// TODO Auto-generated method stub
		ProjectLecture bean = null;
		try {
			bean = projectLectureMapper.selectByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void update(ProjectLecture taskBean) {
		// TODO Auto-generated method stub
		projectLectureMapper.updateByPrimaryKey(taskBean);
	}

	@Override
	public void insert(ProjectLecture taskBean) {
		// TODO Auto-generated method stub
		projectLectureMapper.insert(taskBean);
	}

	@Override
	public void delTask(ProjectLecture taskBean) {
		// TODO Auto-generated method stub
		try {
			projectLectureMapper.deleteByPrimaryKey(taskBean.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<Teacher> selectAllTask(Teacher bean) {
		List<Teacher> list = null;
		try {
			TeacherExample example = new TeacherExample();
			TeacherExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getMajor()!=null && bean.getMajor()!=""){
				criteria.andMajorLike("%"+bean.getMajor()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName()+"%");
			}
			if(bean.getSchoolYear()!=null ){
				criteria.andSchoolYearEqualTo(bean.getSchoolYear());
			}
			list = teacherMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Teacher> selectTaskByPage(Teacher bean, int startRow,
			int pageSize) {
		List<Teacher> list = null;
		try {
			TeacherExample example = new TeacherExample();
			example.setOrderByClause("modify_time desc");
			example.setStartRow(startRow);
			example.setPageSize(pageSize);
			TeacherExample.Criteria criteria = example.createCriteria();
			if(bean.gettId() != null){
				criteria.andTIdEqualTo(bean.gettId());
			}
			if(bean.getMajor()!=null && bean.getMajor()!=""){
				criteria.andMajorLike("%"+bean.getMajor().trim()+"%");
			}
			if(bean.gettName()!=null && bean.gettName()!=""){
				criteria.andTNameLike("%"+bean.gettName().trim()+"%");
			}
			if(bean.getSchoolYear()!=null ){
				criteria.andSchoolYearEqualTo(bean.getSchoolYear());
			}
			list = teacherMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public Teacher selectTaskById(Teacher taskBean) {
		// TODO Auto-generated method stub
		Teacher bean = null;
		try {
			bean = teacherMapper.selectByPrimaryKey(taskBean.gettId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	@Override
	public void delTask(Teacher taskBean) {
		// TODO Auto-generated method stub
		try {
			teacherMapper.deleteByPrimaryKey(taskBean.gettId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void update(Teacher taskBean) {
		// TODO Auto-generated method stub
		teacherMapper.updateByPrimaryKeySelective(taskBean);
	}
	
	

}
