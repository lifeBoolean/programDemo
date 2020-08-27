package net.programDemo.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.programDemo.board.model.BoardVo;
import net.programDemo.board.service.BoardServiceImpl;

@Controller
public class JsonController {
	@Inject
	BoardServiceImpl boardService;
	
	
	@RequestMapping(value = "/jsonView", method = RequestMethod.GET)
	public String jsonView() throws Exception {
		
//		int idx = Integer.parseInt(map.get("idx"));
//		System.out.println("idx: " + map.get("idx"));
//		BoardVo readOne = boardService.readOneBoard(idx);
		
		return "/jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonView", method = RequestMethod.POST)
	public BoardVo jsonView(@RequestBody Map<String, Object> map) throws Exception {
		
		int idx = (int) map.get("idx");
		System.out.println("idx: " + map.get("idx"));
		BoardVo readOne = boardService.readOneBoard(idx);
		System.out.println("readOne: " + readOne);
		
		return readOne;
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonDemo/{id}", method = RequestMethod.GET)
	public Map<String, String> jsonDemo(@PathVariable String id) {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", "123456");
		map.put("location", "SEOUL");
		
		return map;
	}
	
	@RequestMapping(value = "/jsonDemo", method = RequestMethod.GET)
	public String jsonDemo() {
		
				
		return "jsonForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonDemo", method = RequestMethod.POST)
	public Map<String, String> jsonDemo(@RequestBody Map<String, String> map) {
		
		String id = map.get("id");
		String pw = map.get("pw");
		
		map.replace("id", id + "response");
		map.replace("pw", pw + "response");
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonEx", method = RequestMethod.POST)
	public Map<String, String> jsonEx(@RequestBody Map<String, String> map) {
		
		String userId = map.get("userId");
		String userPw = map.get("userPw");
		
		map.replace("userId", userId + "aaaa");
		map.replace("userPw", userPw + "vvvv");
		
		return map;		
	}
	
	@RequestMapping(value = "/jsonForm", method = RequestMethod.GET)
	public String jsonRegister() {
		
		return "jsonForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/jsonProcess", method = RequestMethod.POST)
	public Map<String, Object> jsonProcess(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);
		
		List<Map<String, Object>> mapList = new ArrayList<>();
		
//		Map<String, Object> user = new HashMap<String, Object>();
		map.put("userName", "홍길동");
		map.put("userEmail", "this@daum.net");		
//		mapList.add(map);
//		
//		map.put("name", "kang");
//		map.put("age", 50);
//		map.put("userName", "강호동");
//		map.put("userEmail", "kang@daum.net");
//		mapList.add(map);
				
		return map;
	}

}
