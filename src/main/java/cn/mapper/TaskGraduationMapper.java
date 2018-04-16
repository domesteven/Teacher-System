package cn.mapper;

import cn.bean.TaskGraduation;
import cn.bean.TaskGraduationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskGraduationMapper {
    int countByExample(TaskGraduationExample example);

    int deleteByExample(TaskGraduationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskGraduation record);

    int insertSelective(TaskGraduation record);

    List<TaskGraduation> selectByExample(TaskGraduationExample example);

    TaskGraduation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskGraduation record, @Param("example") TaskGraduationExample example);

    int updateByExample(@Param("record") TaskGraduation record, @Param("example") TaskGraduationExample example);

    int updateByPrimaryKeySelective(TaskGraduation record);

    int updateByPrimaryKey(TaskGraduation record);
}