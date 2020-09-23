package net.programDemo.dao;

import net.programDemo.model.MembersVo;

public interface MembersDao {
	
	public void memberInsert(MembersVo membersVo) throws Exception;
	
	public MembersVo memberLogin(MembersVo memberVo) throws Exception;
	
	public void memberModify(MembersVo membersVo) throws Exception;
	
	public int memberDelete(MembersVo membersVo) throws Exception;

}
