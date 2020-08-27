package net.programDemo.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.model.Pagination;
import net.programDemo.board.service.BoardServiceImpl;
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
	public String jqGrid() throws Exception {
		
		return "/board/jqgrid";
	}
	
	@RequestMapping(value = "/viewGrid", method = RequestMethod.POST)
	@ResponseBody
	public List<BoardVo> viewGrid(BoardVo boardVo, Model model) throws Exception {
//	public String jqgrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		@RequestParam(value = "page", required=false) String page,//page : 몇번째 페이지를 요청했는지
//		@RequestParam(value = "rows", required=false) String rows,//rows : 페이지 당 몇개의 행이 보여질건지
//		@RequestParam(value = "sidx", required=false) String sidx,//sidx : 소팅하는 기준이 되는 인덱스
//		@RequestParam(value = "sord", required=false) String sord//sord : 내림차순 또는 오름차순
//		) {
		
		List<BoardVo> list = boardService.jqGridList();
		model.addAttribute("list", list);
		
		
		
		return list;
		
//		JSONObject jsonObject = new JSONObject();            // Json 데이터를 담을 객체들 생성
//        JSONArray cell = new JSONArray();
//        BoardVo data = null;
//       
//        for(int i=0; i<list.size(); i++) {               // 리스트를 돌려가며 데이터 빈에 저장
//            data = (BoardVo) list.get(i);         
//         
//            JSONObject obj = new JSONObject();
//           
//            // 그리드에서 읽을 값을 key로 지정
//            // Json 객체에 담을 데이터를 차곡차곡 저장
//            obj.put( "idx" , data.getIdx());
//            obj.put( "title" , data.getTitle());
//            obj.put( "writer" , data.getWriter());            
//            obj.put( "regDate"  ,data.getRegDate());
//            obj.put( "hit" , data.getHit());
//                           
//            cell.add(obj);  
//        }  
//       
//        jsonObject.put("rows", cell);
//        response.setContentType("application/x-json; charset=UTF-8");
//        response.getWriter().print(jsonObject);  System.out.println("### jsonObject : "+jsonObject);
//       
//        // 리턴값은 반드시 null ! 리턴값을 주면 그리드가 인식하지 못함.
//        return null;
		
//		System.out.println("여기에 들어오긴함?");
//		// 그리드에 뿌려주려는 데이터를 DB에서나 어디에서 가져온다.
//		JsonObj obj = new JsonObj();
//		
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//
//		int int_page = Integer.parseInt(page);// 1 2 3
//		int perPageNum = (int)Double.parseDouble(rows);
//		
//		
//		// db에서 가져온 데이터의 갯수가 10개라고 가정하고 임의로 수행한다. 그럼 이 키값들을 멤버로 하는 클래스를 가지고 있어야 할 것같다..
//		for(int i= (int_page-1)*perPageNum+1 ; i<(int_page*perPageNum) ; i++){
//			Map<String, Object> map = new HashMap<String, Object>();
//			
//			map.put("id", new String(""+i));
//			map.put("invdate", new String("날짜"+i));
//			map.put("name", new String("이름"+i));
//			map.put("amount", new String("양"+i));
//			map.put("txt", new String("텍스트"+i));
//			
//			list.add(map);
//		}
		
		
		
		
		
		// 그리고 이 JsonObj를 리턴해주면 @ResponseBody 애노테이션 그리고 Jackson라이브러리에 의해
		// json타입으로 페이지에 데이터가 뿌려지게 된다.
	       
//	    obj.setRows(list);  // list<map>형태의 받아온 데이터를 가공해서 셋( 그리드에 뿌려줄 행 데이터들 )
//	    	    
//	    //page : 현재 페이지
//	    obj.setPage(int_page);// 현재 페이지를 매개변수로 넘어온 page로 지정해준다. 
//		
//	    //records : 보여지는 데이터 개수
//	    obj.setRecords(list.size());//?
//		
//	    //total : rows에 의한 총 페이지수
//		// 총 페이지 갯수는 데이터 갯수 / 한페이지에 보여줄 갯수 이런 식
//		int totalPage = (int)Math.ceil(list.size()/Double.parseDouble(rows));
//		obj.setTotal( totalPage ); // 총 페이지 수 (마지막 페이지 번호)
//
//	    return obj;
		
		
//		JSONObject jsonObject = new JSONObject();
//		JSONArray cell = new JSONArray();
//		
//		List<BoardVo> list = boardService.jqGridList();
//		
//		Map
//		map.put("list", list);
		
//		cell.add(list);
//		System.out.println("CELL: " + cell);
//		
//		jsonObject.put("rows", cell);
//		response.setContentType("application/x-json; charset=UTF-8");
//		response.getWriter().print(jsonObject);
//		
//		System.out.println("### list : " + list);
//		System.out.println("### jsonObject : " + jsonObject);
		
//		return list;
		
		
//		JSONObject jsonObject = new JSONObject();
//		JSONArray cell = new JSONArray();
//		
//		
//		JSONObject obj = new JSONObject();
//		obj.put("idx", 1);
//		obj.put("title", "제목");
//		obj.put("writer", "작성자");
//		obj.put("regDate", "20-08-02");
//		obj.put("hit", 11);
//		
//		cell.add(obj);
//		System.out.println("CELL: " + cell);
//		
//		jsonObject.put("rows", cell);
//		response.setContentType("application/x-json; charset=UTF-8");
//		response.getWriter().print(jsonObject);
//		
//		System.out.println("### obj : " + obj);
//		System.out.println("### jsonObject : " + jsonObject);
//		
//		
//		return null;
		
		
		
//		String jsonStr = jObj.toJSONString();
//		System.out.print(jsonStr);
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		
//		Map<String, Object> map = new HashMap();
//		map.put("idx", 1);
//		map.put("title","제목");
//		map.put("writer", "작성자");
//		map.put("regDate", "20-08-02");
//		map.put("hit", 11);
//		
//		list.add(map);
		
//		List<BoardVo> list = boardService.jqGridList();
		
		
		

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