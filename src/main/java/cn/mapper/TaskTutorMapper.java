package cn.mapper;

import cn.bean.TaskTutor;
import cn.bean.TaskTutorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTutorMapper {
    int countByExample(TaskTutorExample example);

    int deleteByExample(TaskTutorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskTutor record);

    int insertSelective(TaskTutor record);

    List<TaskTutor> selectByExample(TaskTutorExample example);

    TaskTutor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskTutor record, @Param("example") TaskTutorExample example);

    int updateByExample(@Param("record") TaskTutor record, @Param("example") TaskTutorExample example);

    int updateByPrimaryKeySelective(TaskTutor record);

    int updateByPrimaryKey(TaskTutor record);
}