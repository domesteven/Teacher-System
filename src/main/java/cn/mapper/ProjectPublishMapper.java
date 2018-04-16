package cn.mapper;

import cn.bean.ProjectPublish;
import cn.bean.ProjectPublishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPublishMapper {
    int countByExample(ProjectPublishExample example);

    int deleteByExample(ProjectPublishExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPublish record);

    int insertSelective(ProjectPublish record);

    List<ProjectPublish> selectByExample(ProjectPublishExample example);

    ProjectPublish selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPublish record, @Param("example") ProjectPublishExample example);

    int updateByExample(@Param("record") ProjectPublish record, @Param("example") ProjectPublishExample example);

    int updateByPrimaryKeySelective(ProjectPublish record);

    int updateByPrimaryKey(ProjectPublish record);
}