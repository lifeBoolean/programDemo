package net.programDemo.model;

public class MembersVo {
	private String userId;
	private String userPw;
	private String userName;
	private String regDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "MembersVo [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", regDate=" + regDate
				+ "]";
	}
	
	

}
