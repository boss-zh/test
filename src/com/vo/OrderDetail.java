package com.vo;

//订单明细实体类
public class OrderDetail {
	private int detailId;
	private int orderId;
	private int pid;
	private double buyPrice;
	private int buySum;

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getBuySum() {
		return buySum;
	}

	public void setBuySum(int buySum) {
		this.buySum = buySum;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [detailId=" + detailId + ", orderId=" + orderId
				+ ", pid=" + pid + ", buyPrice=" + buyPrice + ", buySum="
				+ buySum + "]";
	}
}
