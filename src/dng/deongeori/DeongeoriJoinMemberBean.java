package dng.deongeori;

public class DeongeoriJoinMemberBean {
	private String dng_id;				// 덩어리 아이디.
	private String dng_mem_id;			// 덩어리 서비스 회원 아이디.
	private String dng_mem_joindate;	// 덩어리 가입일시.
	
	public String getDng_id() {
		return dng_id;
	}
	public void setDng_id(String dng_id) {
		this.dng_id = dng_id;
	}
	
	public String getDng_mem_id() {
		return dng_mem_id;
	}
	public void setDng_mem_id(String dng_mem_id) {
		this.dng_mem_id = dng_mem_id;
	}
	
	public String getDng_mem_joindate() {
		return dng_mem_joindate;
	}
	public void setDng_mem_joindate(String dng_mem_joindate) {
		this.dng_mem_joindate = dng_mem_joindate;
	}	
}
