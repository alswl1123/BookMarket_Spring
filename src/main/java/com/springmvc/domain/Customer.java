package com.springmvc.domain;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 3636831123198280235L;
	
	private String customerId; //고객 ID
	private String name;       //고객 이름
	private Address address;   //고객 주소 객체
	private String phone;      //고객 전화 번호

	//마우스 우클릭 > Source > Generate Constructors from Superclass > Object 선택 후 Generate > 수정
	public Customer() {
		this.address = new Address();
	}

	//마우스 우클릭 > Source > Generate Constructors using Fields > customerId와 name 필드 선택 후 Generate > 수정
	public Customer(String customerId, String name) {
		this(); //super 를 this 로 바꿨다.
		this.customerId = customerId;
		this.name = name;
	}

	//마우스 우클릭 > Source > Generate Getters and Setters > 모든 필드 선택 후 Generate
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//마우스 우클릭 > Source > Generate hashCode() and equals() > customerId 필드만 선택 후 Generate > 내용 수정
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Customer other = (Customer) obj;
			
			if(customerId == null) {
				if(other.customerId != null)
					return false;
			} else if(!customerId.equals(other.customerId))
				return false;
			return true;
		}
}
