package com.bigdata2019.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2019.mysite.vo.BoardVo;
import com.bigdata2019.mysite.vo.UserVo;

public class BoardDao {
	public Boolean delete(Long userNo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();					
	
			String sql = 
				" delete" + 
				"   from board" +
				" where user_no = ?"; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, userNo);
			
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("1st error:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Boolean insert(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();					
	
			String sql = 
				" insert" + 
				"   into guestbook" +
				" values (null, ?, ?, now(), ?, ?, ?, ?, ?);"
				; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(7, vo.getHit());
			pstmt.setInt(3, vo.getGroupNo());
			pstmt.setInt(4, vo.getOrderNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());
			
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("1st erro:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public List<BoardVo> findAll(){

		List<BoardVo> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				
				
				String sql = "select no, title, contents, g_no, o_no, depth, date_format(reg_date, '%Y-%c-%d %h:%i:%s'), name, hit \r\n" + 
						"from board, user\r\n" + 
						"order by g_no desc, o_no asc\r\n"+
						"where board.user_no = user.no;";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVo vo = new BoardVo();
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					Integer groupNo = rs.getInt(4);
					Integer orderNo = rs.getInt(5);
					Integer depth = rs.getInt(6);
					String regDate = rs.getString(7);
					String userName	= rs.getString(8);
					Integer hit = rs.getInt(9);
					
					vo.setNo(no);
					vo.setTitle(title);
					vo.setUserName(userName);
					vo.setContents(contents);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setOrderNo(orderNo);
					vo.setDepth(depth);
					vo.setHit(hit);
					result.add(vo);
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println("1st error:" + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return result;
		}
	public BoardVo find(Long no){

		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			
			String sql = "select no, title, contents, date_format(reg_date, '%Y-%c-%d %h:%i:%s'), hit"
					+ " from board where no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				result.setNo(rs.getLong(1));
				result.setTitle(rs.getString(2));
				result.setContents(rs.getString(3));
				result.setRegDate(rs.getString(4));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로딩실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}	
	
	
	
	
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
			
		String url = "jdbc:mysql://localhost:3306/webdb";
		Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		return conn;
	
}

	
}
