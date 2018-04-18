package cn.mapper;

import cn.bean.ProjectLecture;
import cn.bean.ProjectLectureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectLectureMapper {
    int countByExample(ProjectLectureExample example);

    int deleteByExample(ProjectLectureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectLecture record);

    int insertSelective(ProjectLecture record);

    List<ProjectLecture> selectByExample(ProjectLectureExample example);

    ProjectLecture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectLecture record, @Param("example") ProjectLectureExample example);

    int updateByExample(@Param("record") ProjectLecture record, @Param("example") ProjectLectureExample example);

    int updateByPrimaryKeySelective(ProjectLecture record);

    int updateByPrimaryKey(ProjectLecture record);
}