package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.dto.UserDto;
import test.util.DbcpBean;

public class UserDao {
	
	private static UserDao dao;
	
	// static 초기화 블럭 (이 클래스가 최초로 사용될 때 한 번 실행되는 블럭)
	static {
		// static 초기화 작업을 여기서 한다. (UserDao 객체를 생성해서 static 필드에 담는다)
		dao = new UserDao();
	}
	
	// 외부에서 UserDao 객체를 생성하지 못하도록 생성자를 private 로 막는다.
	private UserDao() {}
	
	// UserDao 객체의 참조값을 리턴해주는 public static 메소드 제공
	public static UserDao getInstance() {
		// static 필드에 저장된 dao 의 참조값을 리턴해준다.
		return dao;
	}
	
	// 이메일과 프로필을 수정하는 메소드
	public boolean updateEmailProfile(UserDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email = ?, profileImage = ?, updateAt = SYSDATE
					WHERE userName = ?
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getProfileImage());
			psmt.setString(3, dto.getUserName());
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)psmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
	// 이메일을 수정하는 메소드
	public boolean updateEmail(UserDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email = ?, updateAt = SYSDATE
					WHERE userName = ?
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getUserName());
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)psmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
	// 비밀번호를 수정 반영하는 메소드
	public boolean updatePassword(UserDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;
		
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET password=?, updateAt = SYSDATE
					WHERE userName = ?
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPassword());
			psmt.setString(2, dto.getUserName());
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)psmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
	// userName 을 이용해서 회원 한명의 정보를 리턴하는 메소드
	public UserDto getByUserName(String userName) {
		UserDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT num, password, email, profileImage, role, updateAt, createdAt
					FROM users
					WHERE userName = ?
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			psmt.setString(1, userName);
			// SELECT 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = psmt.executeQuery();
			// 만약 SELECT 되는 row 가 존재한다면
			if(rs.next()) {
				// UserDto 객체를 생성해서
				dto = new UserDto();
				// SELECT 된 정보를 담는다.
				dto.setNum(rs.getLong("num"));
				dto.setUserName(userName);
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setProfileImage(rs.getString("profileImage"));
				dto.setRole(rs.getString("role"));
				dto.setUpdateAt(rs.getString("updateAt"));
				dto.setCreatedAt(rs.getString("createdAt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}
		return dto;
	}
	
	// 회원정보 추가
	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		// 변화된 row 의 개수를 담을 변수 선언하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO users (num, userName, password, email, updateAt, createdAt)
					VALUES(users_seq.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			psmt.setString(1, dto.getUserName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getEmail());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 개수 리턴받기
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
			}
		}
		// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}
}
