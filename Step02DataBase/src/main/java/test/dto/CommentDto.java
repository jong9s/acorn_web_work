package test.dto;

public class CommentDto {
	private int num; // 댓글의 글번호
	private String writer; // 작성자
	private String content; // 내용
	private String targetWriter; // 누구에게 작성한 댓글인지
	private int groupNum; // 댓글의 그룹번호
	private int parentNum; // 부모 글번호
	private String deleted; // 삭제 여부 ('no', 'yes')
	private String createdAt; // 작성일 (to_char로 포맷 가정)
	private String profileImage;

	
	// setter, getter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTargetWriter() {
		return targetWriter;
	}

	public void setTargetWriter(String targetWriter) {
		this.targetWriter = targetWriter;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public int getParentNum() {
		return parentNum;
	}

	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

}
