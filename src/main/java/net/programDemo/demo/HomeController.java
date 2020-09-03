package net.programDemo.demo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.programDemo.common.model.Goods;
import net.programDemo.common.model.MyValue;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}	
	
	@RequestMapping(value = "/testGrid", method = RequestMethod.GET)
	public String testGrid() throws JsonParseException, JsonMappingException, IOException {
		
		String fileName = "server.json";
		String filePath = "D:\\uploads\\files\\" + fileName; // 파일이 저장될 위치
		System.out.println(filePath);		 

//		File file = new File(filePath);
//		if(file.exists()) {
//		System.out.println("파일있음");
//		} else {
//		System.out.println("파일없음");
//		}		
		
		ObjectMapper mapper = new ObjectMapper();
		Goods goods = mapper.readValue(new File(filePath), Goods.class);
		System.out.println("goods: " + goods);
		
		return "/testGrid";
	}
	
	@ResponseBody
	@RequestMapping(value = "testGrid", method = RequestMethod.POST)
	public Object PostTestGrid() throws JsonParseException, JsonMappingException, IOException, ParseException {
		
//		String fileName = "server.json";
//		String filePath = "D:\\uploads\\files\\" + fileName; // 파일이 저장될 위치
//		System.out.println(filePath);		 
		
//		ObjectMapper mapper = new ObjectMapper();
//		Goods goods = mapper.readValue(new File(filePath), Goods.class);
//		System.out.println("goods: " + goods);
		
		return null;

	}
	
	@RequestMapping(value = "/equals")
	public void equals() {
		
		String userId = "";
		String getUserId = "hans";
		
		if(userId.equals("")) {
			System.out.println("비었음");
		} else {
			System.out.println("있음");
			
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST) 
	public HashMap<String, Object> init(@RequestBody MyValue myValue) {
//	public HashMap<String, Object> init(@RequestBody HashMap<String, Object> map) { 
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("MyValue", myValue);
		
		
//		String userId = myValue.getUserId();
//		System.out.println("userId: " + userId);
		
//		map.put("phone", "1234-5678");
		
		return map;
//		JSONObject obj = new JSONObject(map);
//		System.out.println("obj: " + obj);
//		String jsonSend = obj.toJSONString();
//		System.out.println("jsonSend: " + jsonSend);
		
	}

	
}
