-- DEONGEORI_INVITE_MESSAGE
-- 덩어리 초대 메세지 관리 테이블.
-- DEONGEORI_JOIN_MEMBER, DEONGEORI_MEMBER 테이블을 참조합니다.
-- DEONGEORI_JOIN_MEMBER.DNG_ID, DNG_MEM_ID(1) : DEONGEORI_INVITE_MESSAGE.DNG_ID, DNG_INV_FROM(0, N)
-- 1. 해당 덩어리의 멤버만 초대 메세지를 보낼 수 있습니다.
-- DEONGEORI_MEMBER.DNG_MEM_ID(1) : DEONGEORI_INVITE_MESSAGE.DNG_INV_TO(1, N)
-- 1. 덩어리 서비스 회원에게만 초대 메세지를 보낼 수 있습니다.

/* 인덱스 삭제 */
DROP INDEX SCOTT.PK_DEONGEORI_INVITE_MESSAGE;

/* 덩어리 초대 메세지 테이블 삭제 */
DROP TABLE SCOTT.DEONGEORI_INVITE_MESSAGE 
	CASCADE CONSTRAINTS;

/* 덩어리 초대 메세지 테이블 생성 */
CREATE TABLE SCOTT.DEONGEORI_INVITE_MESSAGE (
	DNG_INV_ID INT NOT NULL, /* 초대 메세지 번호 */
	DNG_ID VARCHAR2(15) NOT NULL, /* 초대할 덩어리 아이디 */
	DNG_INV_FROM VARCHAR2(15) NOT NULL, /* 초대한 회원 아이디 */
	DNG_INV_TO VARCHAR2(15) NOT NULL, /* 초대할 회원 아이디 */
	DNG_INV_ISALLOW CHAR(1) DEFAULT 'N', /* 초대 수락 여부 */
	DNG_INV_REGDATE DATE NOT NULL /* 초대일시 */
);

/* 인덱스 생성 */
CREATE UNIQUE INDEX SCOTT.PK_DEONGEORI_INVITE_MESSAGE
	ON SCOTT.DEONGEORI_INVITE_MESSAGE (
		DNG_INV_ID ASC
	);

/* 기본키 설정 */
ALTER TABLE SCOTT.DEONGEORI_INVITE_MESSAGE
	ADD
		CONSTRAINT PK_DEONGEORI_INVITE_MESSAGE
		PRIMARY KEY (
			DNG_INV_ID
		);

/* 테이블, 컬럼 주석 */		
COMMENT ON TABLE SCOTT.DEONGEORI_INVITE_MESSAGE IS '덩어리 초대 메세지 관리 테이블.';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_INV_ID IS '초대 메세지 번호';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_ID IS '초대할 덩어리 아이디';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_INV_FROM IS '초대한 회원 아이디';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_INV_TO IS '초대할 회원 아이디';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_INV_ISALLOW IS '초대 수락 여부';

COMMENT ON COLUMN SCOTT.DEONGEORI_INVITE_MESSAGE.DNG_INV_REGDATE IS '초대일시';

/* 초대 메세지 번호 시퀀스 삭제 */
DROP SEQUENCE dng_inv_seq;

/* 초대 메세지 번호 시퀀스 생성 */
CREATE SEQUENCE dng_inv_seq INCREMENT BY 1 START WITH 1 NOCACHE;

/* 외래키 설정 */
/* 외래키 제약 조건이 만족되지 않으면, 데이터가 삽입되지 않습니다. */
/* 부분 테스트 시에는 제외 시켜 주는 것이 편합니다. */
ALTER TABLE SCOTT.DEONGEORI_INVITE_MESSAGE
	ADD
		CONSTRAINT FK_DJM_TO_DIM
		FOREIGN KEY (
			DNG_ID,
			DNG_INV_FROM
		)
		REFERENCES SCOTT.DEONGEORI_JOIN_MEMBER (
			DNG_ID,
			DNG_MEM_ID
		);

ALTER TABLE SCOTT.DEONGEORI_INVITE_MESSAGE
	ADD
		CONSTRAINT FK_DM_TO_DIM
		FOREIGN KEY (
			DNG_INV_TO
		)
		REFERENCES SCOTT.DEONGEORI_MEMBER (
			DNG_MEM_ID
		);

