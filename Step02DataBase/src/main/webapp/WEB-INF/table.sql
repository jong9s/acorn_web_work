CREATE TABLE users(
	num NUMBER PRIMARY KEY, -- 회원의 고유번호
	userName VARCHAR2(20), -- 아이디
	password VARCHAR2(100) NOT NULL, -- 비밀번호
	email VARCHAR2(50), -- 이메일
	profileImage VARCHAR2(100), -- 프로필 이미지 정보
	role VARCHAR2(10) DEFAULT 'ROLE_USER', -- 역할 USER(일반 사용자) | STAFF(직원) | ADMIN(관리자)
	updateAt DATE, -- 수정 날짜
	createdAt DATE -- 가입 날짜
);

CREATE SEQUENCE users_seq;

CREATE TABLE member(
	num Number PRIMARY KEY,
	name VARCHAR2(20),
	addr VARCHAR2(20)
);

CREATE SEQUENCE book_seq;

CREATE TABLE book (
  num NUMBER PRIMARY KEY,
  title VARCHAR2(200),
  author VARCHAR2(100),
  pub VARCHAR2(100)
);
