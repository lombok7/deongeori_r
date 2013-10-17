-- DEONGEORI_GALLERY_REPLY
-- 덩어리 갤러리 댓글 테이블.
-- DEONGEORI_GALLERY, DEONGEORI_JOIN_MEMBER 테이블을 참조합니다.
-- DEONGEORI_GALLERY.DNG_ID, DNG_GAL_ID(1) : DEONGEORI_GALLERY_REPLY.DNG_ID, DNG_GAL_ID(0, N)
-- 1. 덩어리 갤러리에 존재하는 글에 대해서만 댓글을 작성할 수 있습니다.
-- 2. 덩어리 갤러리 글이 지워지면 댓글도 함께 삭제됩니다. (확인 필요.)
-- DEONGEORI_JOIN_MEMBER.DNG_ID, DNG_MEM_ID(1) : DEONGEORI_GALLERY_REPLY.DNG_ID, DNG_GAL_RE_WRITER(0, N)
-- 1. 해당 덩어리의 멤버만 댓글을 작성할 수 있습니다.

/* 인덱스 삭제 */
DROP INDEX SCOTT.PK_DEONGEORI_GALLERY_REPLY;

/* 덩어리 갤러리 댓글 테이블 삭제 */
DROP TABLE SCOTT.DEONGEORI_GALLERY_REPLY 
	CASCADE CONSTRAINTS;

/* 덩어리 갤러리 댓글 테이블 생성 */
CREATE TABLE SCOTT.DEONGEORI_GALLERY_REPLY (
	DNG_GAL_RE_ID INT NOT NULL, /* 댓글 번호 */
	DNG_ID VARCHAR2(15) NOT NULL, /* 덩어리 아이디 */
	DNG_GAL_ID INT NOT NULL, /* 덩어리 갤러리 이미지 번호 */
	DNG_GAL_RE_WRITER VARCHAR2(15) NOT NULL, /* 댓글 작성자 */
	DNG_GAL_RE_CONTENT VARCHAR2(300) NOT NULL, /* 댓글 내용 */
	DNG_GAL_RE_REGDATE DATE NOT NULL /* 댓글 등록일시 */
);

/* 인덱스 생성 */
CREATE UNIQUE INDEX SCOTT.PK_DEONGEORI_GALLERY_REPLY
	ON SCOTT.DEONGEORI_GALLERY_REPLY (
		DNG_GAL_RE_ID ASC
	);

/* 기본키 설정 */
ALTER TABLE SCOTT.DEONGEORI_GALLERY_REPLY
	ADD
		CONSTRAINT PK_DEONGEORI_GALLERY_REPLY
		PRIMARY KEY (
			DNG_GAL_RE_ID
		);

/* 테이블, 컬럼 주석 */
/* Execute All 에서 에러 나면 여기만 블럭 설정해서 실행해 주세요... */
/* 외래키 설정 SQL문 때문에 그런 듯 한데 이유는 정확히 모르겠음... */		
COMMENT ON TABLE SCOTT.DEONGEORI_GALLERY_REPLY IS '덩어리 갤러리 댓글 테이블.';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_GAL_RE_ID IS '댓글 번호';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_ID IS '어느 덩어리?';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_GAL_ID IS '어떤 이미지의 댓글인지...';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_GAL_RE_WRITER IS '댓글 작성자';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_GAL_RE_CONTENT IS '댓글 내용';

COMMENT ON COLUMN SCOTT.DEONGEORI_GALLERY_REPLY.DNG_GAL_RE_REGDATE IS '댓글 등록일시';

/* 덩어리 갤러리 댓글 번호 시퀀스 삭제 */
DROP SEQUENCE dng_gal_re_seq;

/* 덩어리 게시판 댓글 번호 시퀀스 생성 */
CREATE SEQUENCE dng_gal_re_seq INCREMENT BY 1 START WITH 1 NOCACHE;


/* 외래키 설정 */
/* 외래키 제약 조건이 만족되지 않으면, 데이터가 삽입되지 않습니다. */
/* 부분 테스트 시에는 제외 시켜 주는 것이 편합니다. */
ALTER TABLE SCOTT.DEONGEORI_GALLERY_REPLY
	ADD
		CONSTRAINT FK_DG_TO_DGR
		FOREIGN KEY (
			DNG_ID,
			DNG_GAL_ID
		)
		REFERENCES SCOTT.DEONGEORI_GALLERY (
			DNG_ID,
			DNG_GAL_ID
		)
		ON DELETE CASCADE;

ALTER TABLE SCOTT.DEONGEORI_GALLERY_REPLY
	ADD
		CONSTRAINT FK_DJM_TO_DGR
		FOREIGN KEY (
			DNG_ID,
			DNG_GAL_RE_WRITER
		)
		REFERENCES SCOTT.DEONGEORI_JOIN_MEMBER (
			DNG_ID,
			DNG_MEM_ID
		);
