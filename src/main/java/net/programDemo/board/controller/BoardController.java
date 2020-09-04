package net.programDemo.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;
import net.programDemo.board.service.BoardServiceImpl;
import net.programDemo.common.model.Board;
import net.programDemo.common.model.JqGrid;
import net.programDemo.common.model.JsonAdd;
import net.programDemo.common.model.MyValue;
import net.programDemo.util.FileUtils;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	BoardServiceImpl boardService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model
//			, Pagination pagination
			, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "10") int viewCount
			, @RequestParam(required = false) String searchType
			, @RequestParam(required = false) String keyword
			) throws Exception {
		
		System.out.println("controll page:" +page);
		System.out.println("controll viewCount:" +viewCount);
		System.out.println("controll searchType:" +searchType);
		System.out.println("controll keyword:" +keyword);
		
		Pagination pagination = new Pagination();
		
//		pagination.pageInfo(page, viewCount);
		pagination.pageInfo(page, viewCount, searchType, keyword);
		
		List<BoardVo> list = boardService.listBoard(pagination);
		pagination.setTotalCount(boardService.totalCount(pagination));
		
		model.addAttribute("list", list);		
		model.addAttribute("pagination", pagination);
		
		System.out.println("pagination.toString(): "+pagination.toString());
		
		return "/board/list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		
		return "/board/registerForm";
	}
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String registerProcess(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws Exception {
		
		boardService.registerBoard(boardVo, mpRequest);
		
		return "redirect:/board/list";
	}	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model, BoardVo boardVo, Pagination pagination) throws Exception {
		
		BoardVo readOne = boardService.readOneBoard(boardVo.getIdx());
		model.addAttribute("readOne", readOne);
		model.addAttribute("pagination", pagination);
		
		List<Map<String, Object>> fileList = boardService.selectFileList(boardVo.getIdx());
		model.addAttribute("fileList", fileList);
		
		return "/board/view";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, BoardVo boardVo, Pagination pagination) throws Exception {
		
		BoardVo modifyOne = boardService.readOneBoard(boardVo.getIdx());
		model.addAttribute("modifyOne", modifyOne);		
		
		return "/board/modifyForm";
	}
	
	@RequestMapping(value = "/modifyProcess", method = RequestMethod.POST)
	public String modifyProcess(BoardVo boardVo, Pagination pagination, RedirectAttributes rttr) throws Exception {
		
		boardService.modifyBoard(boardVo);
		rttr.addAttribute("searchType", pagination.getSearchType());
		rttr.addAttribute("keyword", pagination.getKeyword());
		rttr.addAttribute("viewCount", pagination.getViewCount());
		rttr.addAttribute("page", pagination.getPage());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String fileUpload() {
		
		return "/board/uploadForm";
	}
	
	@RequestMapping(value = "/uploadProcess", method = RequestMethod.POST)
	public String uploadProcess(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws Exception {
		
		fileUtils.parseInsertFileInfo(boardVo, mpRequest); 
		
		
//		String filePath = "D:\\upload\\files\\"; // 파일이 저장될 위치
//		String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
//		
//		
//		
//		MultipartFile mutipartFile = mpRequest.getFile("files");
//		System.out.println("mutipartFile: " + mutipartFile);
//		System.out.println("파라미터명" + mutipartFile.getName());
//        System.out.println("파일크기" + mutipartFile.getSize());
//        System.out.println("파일 존재" + mutipartFile.isEmpty());
//        System.out.println("오리지날 파일 이름" + mutipartFile.getOriginalFilename());
//        
//        UUID one = UUID.randomUUID();
//        UUID two = UUID.randomUUID();
//        
//        System.out.println("one: " + one.toString().replaceAll("-", ""));
//        System.out.println("two: " + two.toString().replaceAll("-", ""));
//        
//        String originalFilename = mutipartFile.getOriginalFilename();
//        int Extension = originalFilename.lastIndexOf(".");
//        String originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
//        
//        System.out.println("확장자 위치: " + Extension);
//        System.out.println("확장자: " + originalFileExtension);
//        System.out.println("새 파일명: " + storedFileName);
//        
//        Iterator<String> iterator = mpRequest.getFileNames();
//		System.out.println("iterator: " + iterator);
//		while(iterator.hasNext()) {
//			System.out.println("오리지날 파일 이름" + mutipartFile.getOriginalFilename());
//		}
        
        
//        String path = "D:\\upload\\"; //폴더 경로
//    	File Folder = new File(path);
//
//    	// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//    	if (!Folder.exists()) {
//    		try{
//    		    Folder.mkdir(); //폴더 생성합니다.
//    		    System.out.println("폴더가 생성되었습니다.");
//    	        } 
//    	        catch(Exception e){
//    		    e.getStackTrace();
//    		}        
//             }else {
//    		System.out.println("이미 폴더가 생성되어 있습니다.");
//    	}
//        
//        
//        File file = new File(path + storedFileName);
//        mutipartFile.transferTo(file);		
		
		return "/board/uploadResult";
	}
	
	@RequestMapping(value = "/jqgrid", method = RequestMethod.GET)
	public String jqGrid(Model model, HttpServletRequest request) throws Exception {
		
				
		return "/board/jqgrid";
	}
	
	@RequestMapping(value = "/viewGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object viewGrid(BoardVo boardVo, Model model) throws Exception {
		
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		JqGrid jqGrid = new JqGrid();
		List<BoardVo> list = boardService.jqGridList(boardVo);
		jqGrid.setList(list);
		System.out.println("=======================");		
		System.out.println("jqGrid.toString(): " + jqGrid.toString());
		System.out.println("=======================");
		System.out.println("++++++++++++++++++++++++++++++++++++++");
				
		return jqGrid;
	}
		
	@ResponseBody
	@RequestMapping(value = "/gridDelete", method = RequestMethod.POST)
	public void deleteGridPost(@RequestBody String paramData, BoardVo boardVo) throws Exception {
//		response.setCharacterEncoding("UTF-8");
		System.out.println("paramData: " + paramData);		
	    
	    boardService.gridDelete(paramData, boardVo);
		
//		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/rooping", method = RequestMethod.GET)
	public String rooping() {		
		
		try {
			String url ="jdbc:log4jdbc:oracle:thin:@localhost:1521:ORCL";
			String uid ="scott", pwd = "aass1122";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,uid,pwd);
			
			
			
			for(int i=1; i <= 300; i++) {
				
				String title = "title"+i;
				String writer = "writer"+i;
				String content = "content"+i;
				
				String sql = "INSERT INTO FREEBOARD (IDX, TITLE, WRITER, CONTENT, REGDATE, HIT) "
						+ "VALUES (FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
	
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				pstmt.execute();
			}


		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {				

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return "/board/rooping";
		
	}
}
