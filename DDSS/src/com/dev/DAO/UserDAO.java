package com.dev.DAO;

import java.sql.*;

import com.dev.DTO.UserDTO;
public class UserDAO {
	private static UserDAO instance;
	private UserDAO() {
		
	}
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		
		return instance;
	}
	
	public Connection connect() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/webdb?serverTimezone=Asia/Seoul";
		try {
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection 오류");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("rs.close() 오류 "+ e);
			}
		}
		close(conn, ps);
	}
	
	public void close (Connection conn, PreparedStatement ps) {
		if(ps!= null) {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("ps.close() 오류 "+ e);
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("conn.close() 오류 "+ e);
				e.printStackTrace();
			}
		}
	}
	
	public UserDTO searchUser (String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO user = null;
		conn = connect();
		try {
			pstmt = conn.prepareStatement("Select * from user Where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String userId = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				boolean authority =rs.getBoolean("authority");
				String major = rs.getString("major");
				
				user = new UserDTO(userId, pw, name, authority, major);
			}
		} catch (SQLException e) {
			System.out.println("DB Select 오류 "+ e);
		}finally {
			close(conn, pstmt, rs);
		}
		
		return user;
	}
}
