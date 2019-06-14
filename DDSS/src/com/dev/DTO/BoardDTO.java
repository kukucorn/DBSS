package com.dev.DTO;

public class BoardDTO {
	private String boardNum;
	private String userId;
	private String title;
	private String contents;
	private String pw;
	private String date;
	private int views;
	
	
	
	public BoardDTO(String boardNum, String userId, String title, String contents, String pw, String date, int views) {
		super();
		this.boardNum = boardNum;
		this.userId = userId;
		this.title = title;
		this.contents = contents;
		this.pw = pw;
		this.date = date;
		this.views = views;
	}

	public String getBoardNum() {
		return boardNum;
	}
	
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	
}
