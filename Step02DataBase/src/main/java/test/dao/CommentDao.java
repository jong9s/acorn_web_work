package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.CommentDto;
import test.util.DbcpBean;

public class CommentDao {
	private static CommentDao dao;
	static {
		dao = new CommentDao();
	}
	// 생성자를 private 로 해서 외부에서 객체 생성하지 못하도록
	private CommentDao() {}
	// 자신의 참조값을 리턴해주는 static 메소드를 제공한다.
	public static CommentDao getInstance() {
		return dao;
	}
	// 원글(parentNum) 에 달린 모든 댓글을 리턴하는 메소드
	public List<CommentDto> selectList(int parentNum) {
		List<CommentDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT comments.num, writer, targetWriter, content, deleted, groupNum, comments.createdAt, profileImage
					FROM comments
					INNER JOIN users ON comments.writer = users.userName
					WHERE parentNum = ?
					ORDER BY groupNum DESC, num ASC
					""";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, parentNum);
			rs = psmt.executeQuery();
			if (rs.next()) {
				CommentDto dto = new CommentDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTargetWriter(rs.getString("targetWriter"));
				dto.setContent(rs.getString("content"));
				dto.setParentNum(parentNum);
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setDeleted(rs.getString("deleted"));
				dto.setCreatedAt(rs.getString("createdAt"));
				dto.setProfileImage(rs.getString("profileImage"));
				
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
				e.printStackTrace();
			}
		}	
		return list;
	}
	
	// 댓글 정보를 DB 에 저장하는 메소드
	public boolean insert(CommentDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int rowCount = 0;
				
		try {
			conn = new DbcpBean().getConn();
			String sql = """
				INSERT INTO comments
				(num, writer, targetWriter, content, parentNum, groupNum)
				VALUES(?, ?, ?, ?, ?, ?)
			""";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getNum());
			psmt.setString(2, dto.getWriter());
			psmt.setString(3, dto.getTargetWriter());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getParentNum());
			psmt.setInt(6, dto.getGroupNum());
			rowCount = psmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
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
	
	// 저장할 댓글의 글 번호를 리턴해주는 메소드
	public int getSequence() {
		int num = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT comments_seq.NEXTVAL AS num
					FROM DUAL
					""";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
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
		return num;
	}
	
}
