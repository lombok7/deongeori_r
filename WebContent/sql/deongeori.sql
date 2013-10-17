-- DEONGEORI
-- 덩어리 정보 테이블.
-- DEONGEORI_MEMBER 테이블를 참조합니다. 
-- DEONGEORI_MEMBER.DNG_MEM_ID(1) : DEONGEORI.DNG_ADMIN(0, N)
-- 1. 덩어리 서비스의 회원만 한 개 이상의 덩어리를 생성하여 관리자가 될 수 있습니다.
-- 2. 덩어리 서비스의 회원이 모두 덩어리의 관리자 일 필요는 없습니다.

/* 인덱스 삭제 */
DROP INDEX SCOTT.PK_DEONGEORI;

/* 덩어리 테이블 삭제 */
DROP TABLE SCOTT.DEONGEORI 
	CASCADE CONSTRAINTS;

/* 덩어리 테이블 생성 */
CREATE TABLE SCOTT.DEONGEORI (
	DNG_ID VARCHAR2(15) NOT NULL, /* 덩어리 아이디 */
	DNG_NAME VARCHAR2(50) NOT NULL, /* 덩어리 이름 */
	DNG_IMG VARCHAR2(20), /* 덩어리 로고 이미지 */
	DNG_ADMIN VARCHAR2(15) NOT NULL, /* 덩어리 관리자 */
	DNG_REGDATE DATE NOT NULL /* 덩어리 생성일시 */
);

/* 인덱스 생성 */
CREATE UNIQUE INDEX SCOTT.PK_DEONGEORI
	ON SCOTT.DEONGEORI (
		DNG_ID ASC
	);

/* 기본키 설정 */	
ALTER TABLE SCOTT.DEONGEORI
	ADD
		CONSTRAINT PK_DEONGEORI
		PRIMARY KEY (
			DNG_ID
		);

/* 테이블, 컬럼 주석 */
COMMENT ON TABLE SCOTT.DEONGEORI IS '덩어리 정보 테이블.';

COMMENT ON COLUMN SCOTT.DEONGEORI.DNG_ID IS '14 자리 (m + System.currentTimeMillis())';

COMMENT ON COLUMN SCOTT.DEONGEORI.DNG_NAME IS '덩어리 이름';

COMMENT ON COLUMN SCOTT.DEONGEORI.DNG_IMG IS '덩어리 아이디.확장자';

COMMENT ON COLUMN SCOTT.DEONGEORI.DNG_ADMIN IS '덩어리를 생성한 회원의 아이디.';

COMMENT ON COLUMN SCOTT.DEONGEORI.DNG_REGDATE IS '덩어리 생성일시';

/* 외래키 설정 */
/* 외래키 제약 조건이 만족되지 않으면, 데이터가 삽입되지 않습니다. */
/* 부분 테스트 시에는 제외 시켜 주는 것이 편합니다. */
ALTER TABLE SCOTT.DEONGEORI
	ADD
		CONSTRAINT FK_DM_TO_DEONGEORI
		FOREIGN KEY (
			DNG_ADMIN
		)
		REFERENCES SCOTT.DEONGEORI_MEMBER (
			DNG_MEM_ID
		);

