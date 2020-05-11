package com.vo;

public class Order {
	private int orderId;//订单表主键
	private String orderNo;//订单编号
	private String orderedTime;//下单时间
	private double orderTotalPrice;//订单总金额
	private String orderDesc;//订单描述
	private String receivedPersonInfo;//收货人信息
	private int userId;//用户id
	private int orderState;//订单状态
	
	
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(String orderedTime) {
		this.orderedTime = orderedTime;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getReceivedPersonInfo() {
		return receivedPersonInfo;
	}

	public void setReceivedPersonInfo(String receivedPersonInfo) {
		this.receivedPersonInfo = receivedPersonInfo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}



	

	
}
