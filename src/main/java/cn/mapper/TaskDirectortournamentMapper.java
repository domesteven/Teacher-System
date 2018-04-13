package cn.mapper;

import cn.bean.TaskDirectortournament;
import cn.bean.TaskDirectortournamentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskDirectortournamentMapper {
    int countByExample(TaskDirectortournamentExample example);

    int deleteByExample(TaskDirectortournamentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskDirectortournament record);

    int insertSelective(TaskDirectortournament record);

    List<TaskDirectortournament> selectByExample(TaskDirectortournamentExample example);

    TaskDirectortournament selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskDirectortournament record, @Param("example") TaskDirectortournamentExample example);

    int updateByExample(@Param("record") TaskDirectortournament record, @Param("example") TaskDirectortournamentExample example);

    int updateByPrimaryKeySelective(TaskDirectortournament record);

    int updateByPrimaryKey(TaskDirectortournament record);
}