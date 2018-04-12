package cn.teacher.info.service;

import java.util.List;

import cn.bean.TaskCompany;
import cn.bean.TaskTeaching;
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
	
}
