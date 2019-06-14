package com.dev.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dev.DAO.UserDAO;
import com.dev.DTO.UserDTO;
import com.dev.service.HttpUtil;
import javax.servlet.http.HttpSession;;
public class LoginController extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		if(id.isEmpty()) {
			request.setAttribute("error", "id를 입력해 주세요.");
			HttpUtil.forward(request, response, "/login.jsp");
			return;
		}
		
		UserDAO dao = UserDAO.getInstance();
		
		UserDTO user = dao.searchUser(id);
		
		if(user == null) {
			request.setAttribute("error", "회원정보를 확인해 주세요.");
			HttpUtil.forward(request, response, "/login.jsp");
			return;
		}
		
		if(!pw.equals(user.getPw())) {
			request.setAttribute("error", "비밀번호를 확인해 주세요.");
			HttpUtil.forward(request, response, "/login.jsp");
			return;
		}
		
		request.setAttribute("user", user);
		HttpUtil.forward(request, response, "/login.jsp");
	}
}
