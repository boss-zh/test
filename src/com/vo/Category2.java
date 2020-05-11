package com.vo;

public class Category2 {

	private int cid;
	private String cname;
	private String cdesc;
	private int fid;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "Category2 [cid=" + cid + ", cname=" + cname + ", cdesc="
				+ cdesc + ", fid=" + fid + "]";
	}

}
