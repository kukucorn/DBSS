<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@page import="com.dev.DTO.*"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	${error }

	<form action="/DDSS/user/login" method= "post">
	���̵�<input type="text" name="id">
	��й�ȣ<input type="text" name ="pw">
	<input type="hidden" name="uri" value="login">
	<input type ="submit" value="����">
	</form>
	
	<%UserDTO user = (UserDTO) request.getAttribute("user");
	
	if(user != null){
		session.setAttribute("user",user);
	}
	
	%>
	
	${user.name }<br>
	${user.pw }<br>
	
	<form action="/DDSS/logOut.jsp" method = "post">
		<input type="submit" value="�α׾ƿ�">
 	</form>

</body>
</html>