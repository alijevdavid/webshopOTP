package com.otp.webshop.entity.customer;

public class Customer {
	private Long ID;
	private Long WebshopID;
	private String Name;
	private String Address;
	
	public Long getID() {
		return ID;
	}
	
	public void setID(Long id) {
		ID = id;
	}

	public Long getWebshopID() {
		return WebshopID;
	}
	
	public void setWebshopID(Long webshopID) {
		WebshopID = webshopID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
    @Override
    public String toString() {
        return "Webshop number: " + this.WebshopID + ", Client ID: " + this.ID + ", Name: " + this.Name + ", lives in " + this.Address;
    }
}
