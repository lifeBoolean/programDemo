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
		
		JSONArray students = new JSONArray();
		students.add(student1);
		students.add(student2);
		students.add(student3);
		
		JSONObject professor1 = new JSONObject();
		professor1.put("name", "김교수");
		professor1.put("subject", "영어");
		
		JSONObject professor2 = new JSONObject();
		professor2.put("name", "박교수");
		professor2.put("subject", "과학");
		
		JSONArray professors = new JSONArray();
		professors.add(professor1);
		professors.add(professor2);
		
		
		JSONObject allInfo = new JSONObject();
		allInfo.put("arrStudent", students);
		allInfo.put("arrProfessors", professors);
		
		json = allInfo.toJSONString();
//		System.out.println("json: " + json);
		
	}
}
