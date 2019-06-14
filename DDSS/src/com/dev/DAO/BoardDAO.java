package com.dev.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dev.DTO.BoardDTO;

public class BoardDAO {
	private static BoardDAO instance;
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
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
	
	public ArrayList<BoardDTO> boardList(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = connect();
		
		try {
			
			pstmt = conn.prepareStatement("Select * from posts");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				String boardNum = rs.getString("Board_boardNumber");
				String userId = rs.getString("User_id");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String pw = rs.getString("pw");
				String date = rs.getString("date");
				int views = rs.getInt("views");
				BoardDTO dto = new BoardDTO(boardNum, userId, title, contents, pw, date, views);
				list.add(dto);
				
			}
		} catch (SQLException e) {
			System.out.println("DB select 오류 :" +e);
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public BoardDTO searchBoard(String boardNum) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto= null;
		conn = connect();
		
		try {
			pstmt = conn.prepareStatement("Select * from posts where board_number=?");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String boardNumber = rs.getString("Board_boardNumber");
				String userId = rs.getString("User_id");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String pw = rs.getString("pw");
				String date = rs.getString("date");
				int views = rs.getInt("views");
				dto = new BoardDTO(boardNumber, userId, title, contents, pw, date, views);
			}
		} catch (SQLException e) {
			System.out.println("DB select 오류 :" +e);
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return dto;
		
	}
	public String createBoard() { //board table create
		Connection conn = null;		// generate next boardNumber and return
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String boardNum = null;
		
		conn = connect();
		
		try {
			pstmt = conn.prepareStatement("Select * from board");
			rs = pstmt.executeQuery();
			rs.last();
			boardNum = Integer.toString(rs.getRow() + 1);
			pstmt = conn.prepareStatement("insert board values(?)");
			pstmt.setString(1, boardNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DB create 오류 :" +e);
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		
		return boardNum;
	}
	
	public void insertBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = connect();
		
		try {
			
			pstmt = conn.prepareStatement("insert posts values(?,?,?,?,?,?,?)");
			pstmt.setString(1, board.getBoardNum());
			pstmt.setString(2, board.getUserId());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContents());
			pstmt.setString(5, board.getPw());
			pstmt.setString(6, board.getDate());
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DB insert 오류 :" +e);
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
	
	public void updateBoard(BoardDTO board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = connect();
		
		try {
			pstmt = conn.prepareStatement("Update posts set user_id=?,title=?,contents=?,pw=?,date=?,views=? where boardNum=?");
			
			pstmt.setString(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, board.getPw());
			pstmt.setString(5, board.getDate());
			pstmt.setInt(6, board.getViews());
			pstmt.setString(1, board.getBoardNum());
			
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			System.out.println("DB update 오류 :" +e);
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
		
	}
}
