package net.programDemo.board.dao;

import java.util.List;
import java.util.Map;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;

public interface BoardDao {
	int registerBoard(BoardVo boardVo) throws Exception;
	
	List<BoardVo> listBoard(Pagination pagination) throws Exception;
	
	int totalCount(Pagination pagination) throws Exception;
	
	BoardVo readOneBoard(int idx) throws Exception;
	
	int modifyBoard(BoardVo boardVo) throws Exception;

	int deleteBoard(int idx) throws Exception;
	
	public void insertFile(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectFileList(int idx) throws Exception;
	
	List<BoardVo> jqGridList() throws Exception;
	
}
