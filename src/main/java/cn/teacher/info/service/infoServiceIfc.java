package cn.teacher.info.service;

import java.util.List;

import cn.bean.TaskTeaching;
import cn.bean.Teacher;

public interface infoServiceIfc {

	void updateInfo(Teacher teacher);

	void insertInfo(Teacher teacher);

	List<TaskTeaching> selectAllTaskTeaching(TaskTeaching bean);

	List<TaskTeaching> selectTaskTeachingByPage(TaskTeaching bean,int startRow, int pageSize);

	void delTaskTeaching(TaskTeaching taskTeaching);

	void insert(TaskTeaching taskTeaching);
	
}
