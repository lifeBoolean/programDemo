package net.programDemo.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Inject
	private SqlSession session;
	
	@Override
	public int registerBoard(BoardVo boardVo) throws Exception {
		
		return  session.insert("boardMapper.insert", boardVo);
	}
	
	@Override
	public List<BoardVo> listBoard(Pagination pagination) throws Exception {
		
		return session.selectList("boardMapper.list", pagination);
	}
	
	@Override
	public int totalCount(Pagination pagination) throws Exception {
		
		return session.selectOne("boardMapper.totalCount", pagination);
	}
	
	@Override
	public BoardVo readOneBoard(int idx) throws Exception {
		return session.selectOne("boardMapper.read", idx);
	}
	
	@Override
	public int modifyBoard(BoardVo boardVo) throws Exception {
		
		return session.update("boardMapper.update", boardVo);
	}
	
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		session.insert("boardMapper.insertFile", map);
	}
	
	@Override
	public List<Map<String, Object>> selectFileList(int idx) throws Exception {
		return session.selectList("boardMapper.selectFileList", idx);
	}
	
	@Override
	public List<BoardVo> jqGridList() throws Exception {
		return session.selectList("boardMapper.selectJqList");
	}
	
}
