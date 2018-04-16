package cn.mapper;

import cn.bean.TaskCompany;
import cn.bean.TaskCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskCompanyMapper {
    int countByExample(TaskCompanyExample example);

    int deleteByExample(TaskCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskCompany record);

    int insertSelective(TaskCompany record);

    List<TaskCompany> selectByExample(TaskCompanyExample example);

    TaskCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskCompany record, @Param("example") TaskCompanyExample example);

    int updateByExample(@Param("record") TaskCompany record, @Param("example") TaskCompanyExample example);

    int updateByPrimaryKeySelective(TaskCompany record);

    int updateByPrimaryKey(TaskCompany record);
}