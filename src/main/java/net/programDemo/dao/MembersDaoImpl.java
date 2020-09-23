package net.programDemo.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.programDemo.model.MembersVo;

@Repository
public class MembersDaoImpl implements MembersDao {
	@Inject
	private SqlSession session;

	@Override
	public void memberInsert(MembersVo membersVo) throws Exception {
		
		session.insert("membersMapper.memberInsert", membersVo);
	}

	@Override
	public MembersVo memberLogin(MembersVo memberVo) throws Exception {
		
		MembersVo getMember = session.selectOne("membersMapper.login", memberVo);
		
		return getMember;
	}

	@Override
	public void memberModify(MembersVo membersVo) throws Exception {
		
		session.update("membersMapper.modifyMember", membersVo);
	}

	@Override
	public int memberDelete(MembersVo membersVo) throws Exception {
		
		int n = session.delete("membersMapper.deleteMember", membersVo);
		return n;
	}

}
