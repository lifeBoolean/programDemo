package net.programDemo.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.programDemo.dao.MembersDaoImpl;
import net.programDemo.model.MembersVo;

@Service
public class MembersServiceImpl implements MembersService {
	@Inject
	MembersDaoImpl membersDao;

	@Override
	public void memberInsert(MembersVo membersVo) throws Exception {
		
		membersDao.memberInsert(membersVo);
	}

	@Override
	public MembersVo memberLogin(MembersVo membersVo) throws Exception {
		
		MembersVo getMember = membersDao.memberLogin(membersVo);
		return getMember;
	}

	@Override
	public void memberModify(MembersVo membersVo) throws Exception {
		
		membersDao.memberModify(membersVo);
	}

	@Override
	public int memberDelete(MembersVo membersVo) throws Exception {
		
		int n = membersDao.memberDelete(membersVo);
		
		return n;
	}

}
