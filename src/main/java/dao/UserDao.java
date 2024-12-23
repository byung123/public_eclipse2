package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.User;
import util.DBConnectionMgr;

public class UserDao {
	
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static int save(User user) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			String sql = "insert into user_tb values(0, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return successCount;
	}
	
	public static User findUserByUsername(String username) {
		User findUser = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from user_tb where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				findUser = User.builder()
						.userId(rs.getInt(1))
						.username(rs.getString(2))
						.password(rs.getString(3))
						.name(rs.getString(4))
						.email(rs.getString(5))
						.registerDate(rs.getTimestamp(6).toLocalDateTime())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		
		return findUser;
	}
	
	public static int updateUserInfo(User user) {
		// 계속 작성하기 귀찮아서 Window-Preferences 검색창에 template 검색후
		// Java-Editor-Template 들어가서 add 우른 후 밑의 코드 양식을 복사
		// 다시 사용할 때 내가 지은 이름으로 자동완성을 띄우면 양식이 그대로 나옴
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = pool.getConnection();
			String sql = "update user_tb set name = ?, email = ? where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getUserId());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

		return successCount;
	}
	
	public static int updatePassword(User user) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = pool.getConnection();
			String sql = "update user_tb set password = ? where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getUserId());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

		return successCount;
	}
		
}
