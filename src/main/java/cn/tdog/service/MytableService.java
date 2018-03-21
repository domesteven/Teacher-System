package cn.tdog.service;

import java.util.List;

import cn.tdog.po.Mytable;
import cn.tdog.po.MytableExample;
import cn.tdog.po.User;

public interface MytableService {
	public List<Mytable> findUserMytable() throws Exception;

	public int setUser(Mytable mytable)throws Exception;
	
	public int updateUser (Mytable mytable)throws Exception;
}
