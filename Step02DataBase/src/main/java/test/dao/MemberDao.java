package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;
import test.util.DbcpBean;

/*
 *  Data Access Object 객체를 생성할 클래스 정의하기
 *  
 *  - DB 에 insert, update, delete, select 작업을 하는 코드를 작성한다.
 */
public class MemberDao {

	// 회원번호를 이용해서 회원 1명의 정보를 리턴하는 메소드
	public MemberDto getByNum(int num) {
		// MemberDto 객체의 참조값을 담을 지역변수를 미리 만든다.
		MemberDto dto = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT name, addr
					FROM member
					WHERE num = ?
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			psmt.setInt(1, num);
			// select 문 실행하고 결과를 ResultSet 으로 받아온다.
			rs = psmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 어떤 객체에 담는다
			while (rs.next()) {
				// 객체 생성 후
				dto = new MemberDto();
				// 회원 한명의 정보를 담는다
				dto.setNum(num); // 번호는 매개변수에 있는 내용을 담으면 된다.
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}

	// 회원 전체 목록을 select 해서 List 에 담아서 리턴하는 메소드
	public List<MemberDto> selectAll() {
		// 회원정보를 누적시킬 ArrayList 객체 미리 준비하기
		List<MemberDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT num, name, addr
					FROM member
					ORDER BY num ASC
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet 으로 받아온다.
			rs = psmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 어떤 객체에 담는다
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				// 회원 한명의 정보가 담긴 MemberDto 객체의 참조값을 List 에 누적시키기
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		// 변화된 row 의 개수를 담을 변수 선언하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE member SET name = ?, addr = ?
					WHERE num = ?
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getAddr());
			psmt.setInt(3, dto.getNum());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 개수 리턴받기
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
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

	// 회원 한명의 정보를 DB 에서 삭제하고 성공여부를 리턴하는 메소드
	public boolean deleteByNum(int num) {
		Connection conn = null;
		PreparedStatement psmt = null;
		// 변화된 row 의 개수를 담을 변수 선언하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					DELETE FROM member WHERE num = ?
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			psmt.setInt(1, num);
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 개수 리턴받기
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
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

	// 회원 한명의 정보를 DB 에 저장하고 성공여부를 리턴하는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		// 변화된 row 의 개수를 담을 변수 선언하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO member (num, name, addr)
					VALUES(member_sequence.NEXTVAL, ?, ?)
					""";
			psmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getAddr());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 개수 리턴받기
			rowCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
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
