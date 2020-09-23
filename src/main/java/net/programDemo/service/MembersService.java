package net.programDemo.service;

import net.programDemo.model.MembersVo;

public interface MembersService {
	
	void memberInsert(MembersVo membersVo) throws Exception;
	
	public MembersVo memberLogin(MembersVo membersVo) throws Exception;
	
	public void memberModify(MembersVo membersVo) throws Exception;
	
	public int memberDelete(MembersVo membersVo) throws Exception;

}
