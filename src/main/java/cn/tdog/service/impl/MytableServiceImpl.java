package cn.tdog.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.tdog.mapper.MytableMapper;
import cn.tdog.po.Mytable;
import cn.tdog.po.MytableExample;
import cn.tdog.service.MytableService;

public class MytableServiceImpl implements MytableService {
	@Autowired
	private MytableMapper mytableMapper;

	public List<Mytable> findUserMytable() throws Exception {
		MytableExample example = new MytableExample();
		example.setStart(0);
		example.setLimit(20);
		
		return mytableMapper.selectAll(example);
	}
	
	@Transactional
	public int setUser(Mytable mytable) throws Exception{
		// TODO Auto-generated method stub
//		String id = mytableMapper.selectSeq();
//		System.out.println("================="+id);
//		mytable.setId(new BigDecimal(id));
//		Mytable newT = new Mytable();
//		newT.setId(8);
//		newT.setName("秦叶");
//		newT.setAge(new BigDecimal(55));
		
//		int t_id = mytableMapper.selectSeq();
//		mytable.setId(t_id);
		int res=mytableMapper.insert(mytable);
		return res;
		
	}
	
	@Transactional
	public int updateUser(Mytable mytable) throws Exception {
	
		mytable.setName("叶凡1");
		mytable.setAge(new BigDecimal(88));
		mytable.setId(57);
		mytableMapper.updateById(mytable);

		return 1;
	}
	
	

}
