package com.vo;


public class Category {

	private int cid;
	private String cname1;
	private String cdesc;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname1;
	}

	public void setCname(String cname) {
		this.cname1 = cname;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname1 + ", cdesc=" + cdesc
				+ "]";
	}

}
