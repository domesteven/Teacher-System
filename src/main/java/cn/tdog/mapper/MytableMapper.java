package cn.tdog.mapper;

import cn.tdog.po.Mytable;
import cn.tdog.po.MytableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MytableMapper {
    int countByExample(MytableExample example);

    int deleteByExample(MytableExample example);

    int insert(Mytable record);

    int insertSelective(Mytable record);

    List<Mytable> selectByExample(MytableExample example);
    
    List<Mytable> selectAll(MytableExample example);

    int updateByExampleSelective(@Param("record") Mytable record, @Param("example") MytableExample example);

    int updateByExample(@Param("record") Mytable record, @Param("example") MytableExample example);
    
    int selectSeq();
    
    void updateById(Mytable mytable);
}