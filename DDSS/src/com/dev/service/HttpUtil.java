package com.dev.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	
	public static void forward (HttpServletRequest request , HttpServletResponse response , String path) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}catch (Exception ex) {
			System.out.println("forward ���� : " + ex);
		}
	}
	
}
