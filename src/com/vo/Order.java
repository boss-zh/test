package com.vo;

public class Order {
	private int orderId;//����������
	private String orderNo;//�������
	private String orderedTime;//�µ�ʱ��
	private double orderTotalPrice;//�����ܽ��
	private String orderDesc;//��������
	private String receivedPersonInfo;//�ջ�����Ϣ
	private int userId;//�û�id
	private int orderState;//����״̬
	
	
	
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
