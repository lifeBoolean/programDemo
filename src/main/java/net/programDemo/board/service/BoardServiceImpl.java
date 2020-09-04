package net.programDemo.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.programDemo.board.dao.BoardDao;
import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;
import net.programDemo.util.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	BoardDao boardDao;
	
	@Override
	public void registerBoard(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws Exception {
		
		int n = boardDao.registerBoard(boardVo);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardVo, mpRequest); 
		int size = list.size();
		
		System.out.println("size: " + size);
		
		for(int i=0; i<size; i++){ 
			boardDao.insertFile(list.get(i));
		}
		
	}
	
	@Override
	public List<BoardVo> listBoard(Pagination pagination) throws Exception {

		return boardDao.listBoard(pagination);
	}
	
	@Override
	public int totalCount(Pagination pagination) throws Exception {
		
		return boardDao.totalCount(pagination);
	}
		
	@Override
	public BoardVo readOneBoard(int idx) throws Exception {

		return boardDao.readOneBoard(idx);
	}
	
	@Override
	public int modifyBoard(BoardVo boardVo) throws Exception {
		
		return boardDao.modifyBoard(boardVo);
	}
	
	public List<Map<String, Object>> selectFileList(int idx) throws Exception {
		
		return boardDao.selectFileList(idx);
	}
	
	public List<BoardVo> jqGridList(BoardVo boardVo) throws Exception {
		
		return boardDao.jqGridList();
	}
	
	public void gridDelete(String paramData, BoardVo boardVo) throws Exception {
		
		JSONParser parser = new JSONParser(); //–JSON Parser 생성
	    JSONObject jsonObj = (JSONObject)parser.parse(paramData); //– 넘어온 문자열을 JSON 객체로 변환
	    System.out.println("jsonObj: " + jsonObj);
	    
	    List list = (ArrayList) jsonObj.get("data");
	    System.out.println("list: " + list);
	    
	    for(int i=0; i<list.size(); i++) {
	    	JSONObject boardVoList = (JSONObject) list.get(i);
	    	System.out.println("listGET: " + boardVoList);
	    	int idx = Integer.parseInt((String) boardVoList.get("idx"));
	    	System.out.println("idx: " + idx);
	    	boardVo.setIdx(idx);
	    	System.out.println("getIdx: " + boardVo.getIdx());
	    	boardDao.deleteBoard(idx);
	    	
	    }
	    
		
	}
	
	public void gridModify(String paramData, BoardVo boardVo) throws Exception {
		
		
		
		
	}
	
}
