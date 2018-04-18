package cn.mapper;

import cn.bean.ProjectPerson;
import cn.bean.ProjectPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPersonMapper {
    int countByExample(ProjectPersonExample example);

    int deleteByExample(ProjectPersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPerson record);

    int insertSelective(ProjectPerson record);

    List<ProjectPerson> selectByExample(ProjectPersonExample example);

    ProjectPerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPerson record, @Param("example") ProjectPersonExample example);

    int updateByExample(@Param("record") ProjectPerson record, @Param("example") ProjectPersonExample example);

    int updateByPrimaryKeySelective(ProjectPerson record);

    int updateByPrimaryKey(ProjectPerson record);
}