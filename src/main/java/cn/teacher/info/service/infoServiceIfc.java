package cn.teacher.info.service;

import java.util.List;

import cn.bean.ProjectPublish;
import cn.bean.TaskCompany;
import cn.bean.TaskDirectortournament;
import cn.bean.TaskGraduation;
import cn.bean.TaskTeaching;
import cn.bean.TaskTutor;
import cn.bean.Teacher;

public interface infoServiceIfc {

	void updateInfo(Teacher teacher);

	void insertInfo(Teacher teacher);

	List<TaskTeaching> selectAllTaskTeaching(TaskTeaching bean);

	List<TaskTeaching> selectTaskTeachingByPage(TaskTeaching bean,int startRow, int pageSize);

	void delTaskTeaching(TaskTeaching taskTeaching);

	void insert(TaskTeaching taskTeaching);

	TaskTeaching selectTaskTeachingById(TaskTeaching taskTeaching);

	void update(TaskTeaching taskTeaching);

	List<TaskCompany> selectAllTaskCompany(TaskCompany bean);

	List<TaskCompany> selectTaskCompanyByPage(TaskCompany bean, int startRow,
			int pageSize);

	void delTaskCompany(TaskCompany taskCompany);

	void insert(TaskCompany taskCompany);

	void update(TaskCompany taskCompany);

	TaskCompany selectTaskCompanyById(TaskCompany taskCompany);

	void insert(TaskGraduation taskBean);

	void update(TaskGraduation taskBean);

	TaskGraduation selectTaskCompanyById(TaskGraduation taskBean);

	List<TaskGraduation> selectAllTaskCompany(TaskGraduation bean);

	List<TaskGraduation> selectTaskCompanyByPage(TaskGraduation bean,
			int startRow, int pageSize);

	void delTaskGraduation(TaskGraduation taskBean);

	List<TaskDirectortournament> selectAllTask(TaskDirectortournament bean);

	List<TaskDirectortournament> selectTaskByPage(TaskDirectortournament bean,
			int startRow, int pageSize);

	void delTask(TaskDirectortournament taskBean);

	void insert(TaskDirectortournament taskBean);

	void update(TaskDirectortournament taskBean);

	TaskDirectortournament selectTaskById(TaskDirectortournament taskBean);

	List<TaskTutor> selectAllTask(TaskTutor bean);

	List<TaskTutor> selectTaskByPage(TaskTutor bean, int startRow,
			int pageSize);

	TaskTutor selectTaskById(TaskTutor taskBean);

	void update(TaskTutor taskBean);

	void insert(TaskTutor taskBean);

	void delTask(TaskTutor taskBean);

	List<Teacher> selectAllTeacher();

	List<ProjectPublish> selectAllTask(ProjectPublish bean);

	List<ProjectPublish> selectTaskByPage(ProjectPublish bean, int startRow,
			int pageSize);

	ProjectPublish selectTaskById(ProjectPublish taskBean);

	void update(ProjectPublish taskBean);

	void insert(ProjectPublish taskBean);

	void delTask(ProjectPublish taskBean);

	
}
