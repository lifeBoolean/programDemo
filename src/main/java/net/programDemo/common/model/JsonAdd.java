package net.programDemo.common.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; //JSON객체를 파싱해오는데 사용
import org.json.simple.parser.ParseException; //예외처리

public class JsonAdd {
	
	public String json;
	
	public JsonAdd() {
	}

	public void jsonGet() {

		JSONObject student1 = new JSONObject();
		student1.put("name", "한효주");
		student1.put("id", "hans");
		student1.put("age", "30");
		
		JSONObject student2 = new JSONObject();
		student2.put("name", "강호동");
		student2.put("id", "kang");
		student2.put("age", "50");
		
		JSONObject student3 = new JSONObject();
		student3.put("name", "이승엽");
		student3.put("id", "lee");
		student3.put("age", "40");
		
		JSONArray ary = new JSONArray();
		ary.add(student1);
		ary.add(student2);
		ary.add(student3);
		
		JSONObject all = new JSONObject();
		all.put("info", ary);
		
		json = all.toJSONString();
		System.out.println("json: " + json);
		
		
		
	}
}
