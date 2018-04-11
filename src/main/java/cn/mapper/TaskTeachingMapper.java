package cn.mapper;

import cn.bean.TaskTeaching;
import cn.bean.TaskTeachingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTeachingMapper {
    int countByExample(TaskTeachingExample example);

    int deleteByExample(TaskTeachingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskTeaching record);

    int insertSelective(TaskTeaching record);

    List<TaskTeaching> selectByExample(TaskTeachingExample example);

    TaskTeaching selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskTeaching record, @Param("example") TaskTeachingExample example);

    int updateByExample(@Param("record") TaskTeaching record, @Param("example") TaskTeachingExample example);

    int updateByPrimaryKeySelective(TaskTeaching record);

    int updateByPrimaryKey(TaskTeaching record);
}