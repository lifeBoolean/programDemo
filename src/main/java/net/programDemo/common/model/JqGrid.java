package net.programDemo.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JqGrid {
	
	@JsonProperty("page")
	private int page = 1;
	
	@JsonProperty("records")
	private int records = 20;
	
	@JsonProperty("total")
	private int total = 30;
	
	@JsonProperty("rows")
	private Object list;

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "JqGrid [list=" + list + "]";
	}
	
	

	

}
