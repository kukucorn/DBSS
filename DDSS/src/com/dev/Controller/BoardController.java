package com.dev.Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class BoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;
	
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/WebProgramming/board/insert", new BoardInsertController());
		list.put("/WebProgramming/board/search", new BoardSearchController());
		list.put("/WebProgramming/board/update", new BoardUpdateController());
		list.put("/WebProgramming/board/delete", new BoardDeleteController());
		list.put("/WebProgramming/board/list", new BoardListController());
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}
}
