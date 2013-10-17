-- BOARD
-- 게시판 테이블.
-- DEONGEORI_MEMBER 테이블을 참조합니다.
-- DEONGEORI_MEMBER.MEM_ID(1) : BOARD.BOARD_WRITER(0, N)
-- 1. 덩어리 서비스의 회원만 게시판에 글을 작성할 수 있습니다.

/* 인덱스 삭제 */
DROP INDEX SCOTT.PK_BOARD;

/* 게시판 테이블 삭제 */
DROP TABLE SCOTT.BOARD 
	CASCADE CONSTRAINTS;

/* 게시판 테이블 생성 */
CREATE TABLE SCOTT.BOARD (
	BOARD_ID INT NOT NULL, /* 글 번호 */
	BOARD_WRITER VARCHAR2(15) NOT NULL, /* 글 작성자 */
	BOARD_TITLE VARCHAR2(100) NOT NULL, /* 글 제목 */
	BOARD_CONTENT VARCHAR2(2000) NOT NULL, /* 글 내용 */
	BOARD_PWD VARCHAR2(10) NOT NULL, /* 글 비밀번호 */
	BOARD_READCOUNT INT DEFAULT 0, /* 글 조회 수 */
	BOARD_REGDATE DATE NOT NULL /* 글 등록일시 */
);

/* 인덱스 생성 */
CREATE UNIQUE INDEX SCOTT.PK_BOARD
	ON SCOTT.BOARD (
		BOARD_ID ASC
	);

/* 기본키 설정 */
ALTER TABLE SCOTT.BOARD
	ADD
		CONSTRAINT PK_BOARD
		PRIMARY KEY (
			BOARD_ID
		);

/* 테이블, 컬럼 주석. */		
COMMENT ON TABLE SCOTT.BOARD IS '게시판 테이블.';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_ID IS '글 번호';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_WRITER IS '글을 작성한 회원의 아이디.';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_TITLE IS '글 제목';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_CONTENT IS '글 내용';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_PWD IS '글 비밀번호';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_READCOUNT IS '글 조회 수';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_REGDATE IS '글 등록일시';

/* 글 번호 시퀀스 삭제 */
DROP SEQUENCE board_seq;

/* 글 번호 시퀀스 생성 */
CREATE SEQUENCE board_seq INCREMENT BY 1 START WITH 1 NOCACHE;

/* 외래키 설정 */
/* 외래키 제약 조건이 만족되지 않으면, 데이터가 삽입되지 않습니다. */
/* 부분 테스트 시에는 제외 시켜 주는 것이 편합니다. */
ALTER TABLE SCOTT.BOARD
	ADD
		CONSTRAINT FK_DM_TO_BOARD
		FOREIGN KEY (
			BOARD_WRITER
		)
		REFERENCES SCOTT.DEONGEORI_MEMBER (
			DNG_MEM_ID
		);
		
		