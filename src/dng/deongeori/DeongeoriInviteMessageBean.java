package dng.deongeori;

public class DeongeoriInviteMessageBean {
	private int dng_inv_id;			// 덩어리 초대 메세지 번호.
	private String dng_id;			// 초대할 덩어리 아이디.
	private String dng_inv_from;	// 초대한 회원 아이디.
	private String dng_inv_to;		// 초대할 회원 아이디.
	private char dng_inv_isallow;	// 초대 수락 여부.
	private String dng_inv_regdate;	// 초대일시.
	
	public int getDng_inv_id() {
		return dng_inv_id;
	}
	public void setDng_inv_id(int dng_inv_id) {
		this.dng_inv_id = dng_inv_id;
	}
	
	public String getDng_id() {
		return dng_id;
	}
	public void setDng_id(String dng_id) {
		this.dng_id = dng_id;
	}
	
	public String getDng_inv_from() {
		return dng_inv_from;
	}
	public void setDng_inv_from(String dng_inv_from) {
		this.dng_inv_from = dng_inv_from;
	}
	
	public String getDng_inv_to() {
		return dng_inv_to;
	}
	public void setDng_inv_to(String dng_inv_to) {
		this.dng_inv_to = dng_inv_to;
	}
	
	public char getDng_inv_isallow() {
		return dng_inv_isallow;
	}
	public void setDng_inv_isallow(char dng_inv_isallow) {
		this.dng_inv_isallow = dng_inv_isallow;
	}
	
	public String getDng_inv_regdate() {
		return dng_inv_regdate;
	}
	public void setDng_inv_regdate(String dng_inv_regdate) {
		this.dng_inv_regdate = dng_inv_regdate;
	}	
}
