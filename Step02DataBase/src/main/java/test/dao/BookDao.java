package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.BookDto;
import test.util.DbcpBean;

public class BookDao {

	// 책 번호로 한 권의 책 정보 조회
	public BookDto getByNum(int num) {
		BookDto dto = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT title, author, pub
					FROM book
					WHERE num = ?
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new BookDto();
				dto.setNum(num);
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPub(rs.getString("pub"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (psmt != null) psmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return dto;
	}

	// 전체 책 목록 조회
	public List<BookDto> selectAll() {
		List<BookDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT num, title, author, pub
					FROM book
					ORDER BY num ASC
					""";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BookDto dto = new BookDto();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPub(rs.getString("pub"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (psmt != null) psmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return list;
	}

	// 책 정보 수정
	public boolean update(BookDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE book SET title = ?, author = ?, pub = ?
					WHERE num = ?
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getAuthor());
			psmt.setString(3, dto.getPub());
			psmt.setInt(4, dto.getNum());
			rowCount = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (psmt != null) psmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 책 삭제
	public boolean deleteByNum(int num) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = "DELETE FROM book WHERE num = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rowCount = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (psmt != null) psmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 책 추가
	public boolean insert(BookDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO book (num, title, author, pub)
					VALUES(book_seq.NEXTVAL, ?, ?, ?)
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getAuthor());
			psmt.setString(3, dto.getPub());
			rowCount = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (psmt != null) psmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
}
