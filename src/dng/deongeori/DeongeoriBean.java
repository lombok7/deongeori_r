package dng.deongeori;

public class DeongeoriBean {
	private String dng_id;		// 덩어리 아이디.
	private String dng_name;	// 덩어리 이름.
	private String dng_img;		// 덩어리 로고 이미지.
	private String dng_admin;	// 덩어리 관리자.	
	private String dng_regdate;	// 덩어리 생성일시.
	
	public String getDng_id() {
		return dng_id;
	}
	public void setDng_id(String dng_id) {
		this.dng_id = dng_id;
	}
	
	public String getDng_name() {
		return dng_name;
	}
	public void setDng_name(String dng_name) {
		this.dng_name = dng_name;
	}
	
	public String getDng_img() {
		return dng_img;
	}
	public void setDng_img(String dng_img) {
		this.dng_img = dng_img;
	}
	
	public String getDng_admin() {
		return dng_admin;
	}	
	public void setDng_admin(String dng_admin) {
		this.dng_admin = dng_admin;
	}
	
	public String getDng_regdate() {
		return dng_regdate;
	}
	public void setDng_regdate(String dng_regdate) {
		this.dng_regdate = dng_regdate;
	}	
}
