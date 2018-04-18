package cn.mapper;

import cn.bean.ProjectSocialservice;
import cn.bean.ProjectSocialserviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSocialserviceMapper {
    int countByExample(ProjectSocialserviceExample example);

    int deleteByExample(ProjectSocialserviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSocialservice record);

    int insertSelective(ProjectSocialservice record);

    List<ProjectSocialservice> selectByExample(ProjectSocialserviceExample example);

    ProjectSocialservice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSocialservice record, @Param("example") ProjectSocialserviceExample example);

    int updateByExample(@Param("record") ProjectSocialservice record, @Param("example") ProjectSocialserviceExample example);

    int updateByPrimaryKeySelective(ProjectSocialservice record);

    int updateByPrimaryKey(ProjectSocialservice record);
}