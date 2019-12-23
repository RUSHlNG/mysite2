package com.bigdata2019.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bigdata2019.mysite.vo.UserVo;

public class UserDao {
	public Boolean insert(UserVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();					
	
			String sql = 
				" insert" + 
				"   into user" +
				" values (no, ?, ?, ?, ?)"; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
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
	
	
	public UserVo find(String email, String password){

		UserVo result = null;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				
				
				String sql = "select no, name from user where email=? and password=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,  email);
				pstmt.setString(2,  password);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = new UserVo();
					result.setNo(rs.getLong(1));
					result.setName(rs.getString(2));
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

	public UserVo find(Long no){

			UserVo result = null;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				
				
				String sql = "select no, name email, gender from user where no=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, no);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = new UserVo();
					result.setNo(rs.getLong(1));
					result.setName(rs.getString(2));
					result.setEmail(rs.getString(3));
					result.setGender(rs.getString(4));
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
