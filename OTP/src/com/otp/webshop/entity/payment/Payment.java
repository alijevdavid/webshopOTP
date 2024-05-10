package com.otp.webshop.entity.payment;

import java.time.LocalDate;
import com.otp.webshop.formatter.CurrencyFormatter;
import com.otp.webshop.entity.paymentmethod.PaymentMethod;

public class Payment {
	private Long ClientID;
	private Long WebshopID;
	private PaymentMethod PaymentMethod;
	private Integer Amount_HUF;
	private String BankAccountNumber;
	private String CardNumber;
	private LocalDate DateOfPayment;
	
	public Long getClientID() {
		return ClientID;
	}
	public void setClientID(Long clientID) {
		ClientID = clientID;
	}
	public Long getWebshopID() {
		return WebshopID;
	}
	public void setWebshopID(Long webshopID) {
		WebshopID = webshopID;
	}
	public PaymentMethod getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMode) {
		PaymentMethod = paymentMode;
	}
	public Integer getAmount_HUF() {
		return Amount_HUF;
	}
	public void setAmount_HUF(Integer amount_HUF) {
		Amount_HUF = amount_HUF;
	}
	public String getBankAccountNumber() {
		return BankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		BankAccountNumber = bankAccountNumber;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public LocalDate getDateOfPayment() {
		return DateOfPayment;
	}
	public void setDateOfPayment(LocalDate dateOfPayment) {
		DateOfPayment = dateOfPayment;
	}
	
    @Override
    public String toString() {
    	CurrencyFormatter formatter = new CurrencyFormatter();
    	
    	String currHuf = formatter.fromIntToCurrency("hu", "HUF", this.Amount_HUF);

    	if(this.PaymentMethod == com.otp.webshop.entity.paymentmethod.PaymentMethod.CARD) {
            return "Webshop number: " + this.WebshopID + ", Client ID: " + this.ClientID + ", Payment method: " + this.PaymentMethod.toString() + ", paid " + currHuf
            		+ ", Card number: " + this.CardNumber + ", payment date: " + this.DateOfPayment.toString();
    	}
    	
    	else {
            return "Webshop number: " + this.WebshopID + ", Client ID: " + this.ClientID + ", Payment method: " + this.PaymentMethod.toString() + ", paid " + currHuf
            		+ ", Bank account number: " + this.BankAccountNumber + ", payment date: " + this.DateOfPayment.toString();
    	}
    }
}
