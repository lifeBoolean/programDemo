package net.programDemo.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;

public interface BoardService {
	
	void registerBoard(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws Exception;
	
	List<BoardVo> listBoard(Pagination pagination) throws Exception;
	
	public int totalCount(Pagination pagination) throws Exception;
	
	BoardVo readOneBoard(int idx) throws Exception;
	
	int modifyBoard(BoardVo boardVo) throws Exception;
	
	List<Map<String, Object>> selectFileList(int idx) throws Exception;
	
	List<BoardVo> jqGridList(BoardVo boardVo) throws Exception;
	
	void gridModify(String paramData, BoardVo boardVo) throws Exception;
	
	void gridDelete(String paramData, BoardVo boardVo) throws Exception;
	
}
