package com.otp.webshop.data;

import java.util.*;
import java.util.stream.Collectors;

import com.otp.webshop.entity.customer.Customer;
import com.otp.webshop.entity.payment.Payment;
import com.otp.webshop.entity.paymentmethod.PaymentMethod;
import com.otp.webshop.exception.NotFoundException;

public class Cache {
	
    private static Cache cache;
	private static List<Customer> customers = new ArrayList<Customer>();
	private static List<Payment> payments = new ArrayList<Payment>();
	
    private Cache() { }

    public static synchronized Cache getInstance() {
        if (cache == null) {
        	cache = new Cache();
        }
        return cache;
    }
    
	public List<Customer> getAllCustomers(){
		return new ArrayList<Customer>(customers);
	}
	
	public List<Payment> getAllPayments(){
		return new ArrayList<Payment>(payments);
	}
	
	public Customer getCustomer(Long WebshopID, Long ClientID) {
		Customer customer = Cache.customers.stream().filter(c -> c.getWebshopID().equals(WebshopID) && c.getID().equals(ClientID)).findFirst().get();
		
		if(customer != null) {
			return customer;
		}
		
		return null;
	}
	
	public Payment getPayment(Long WebshopID, Long ClientID) {
		
		Payment payment = Cache.payments.stream().filter(p -> p.getWebshopID().equals(WebshopID) && p.getClientID().equals(ClientID)).findFirst().get();
		if(payment != null) {
			return payment;
		}
		
		return null;
	}
	
	public void addCustomer(Customer newCustomer) {
		Cache.customers.add(newCustomer);
	}
	
	public void addPayment(Payment newPayment) {
		
		Cache.payments.add(newPayment);
	}
	
	public void updateCustomer(Customer oldCustomer, Customer newCostumer) throws Exception {
		Customer customerToUpdate = this.getCustomer(oldCustomer.getWebshopID(), oldCustomer.getID());
		
		if(customerToUpdate != null) {
			Cache.customers.remove(oldCustomer);
			Cache.customers.add(newCostumer);
		}
		
		else {
			throw new NotFoundException("The customer to update does not exist!");
		}
	}
	
	public void deleteCustomer(Customer customerToDel) throws Exception {
		Customer customerToDelete = this.getCustomer(customerToDel.getWebshopID(), customerToDel.getID());
		
		if(customerToDelete != null) {
			Cache.customers.remove(customerToDelete);
		}
		
		else {
			throw new NotFoundException("The customer to delete does not exist!");
		}
	}
	
	public void deletePayment(Payment paymentToDel) throws Exception {
		Payment paymentToDelete = this.getPayment(paymentToDel.getWebshopID(), paymentToDel.getClientID());
		
		if(paymentToDelete != null) {
			Cache.payments.remove(paymentToDelete);
		}
		
		else {
			throw new NotFoundException("The payment to delete does not exist!");
		}
	}
	
	public List<Customer> getAllCustomerFromSameWebshop(Long WebshopID) {
		List<Customer> customersInSameWebshop = Cache.customers.stream().filter(c -> c.getWebshopID().equals(WebshopID)).collect(Collectors.toList());
		
		if(customersInSameWebshop.size() != 0) {
			return customersInSameWebshop;
		}
		
		else {
			return new ArrayList<Customer>();
		}
	}
	
	public List<Payment> getAllPaymentFromSameWebshop(Long WebshopID) {
		List<Payment> paymentsInSameWebshop = Cache.payments.stream().filter(p -> p.getWebshopID().equals(WebshopID)).collect(Collectors.toList());
		
		if(paymentsInSameWebshop.size() != 0) {
			return paymentsInSameWebshop;
		}
		
		else {
			return new ArrayList<Payment>();
		}
	}
	
	public int getCustomersTotalSpending(Long customerID) {
		int allPayments = 0;
		List<Payment> customersAllPayments = Cache.payments.stream().filter(p -> p.getClientID().equals(customerID)).collect(Collectors.toList());
		
		for(Payment p : customersAllPayments) {
			allPayments += p.getAmount_HUF();
		}
		
		return allPayments;
	}
	
	public int getWebshopsTotalCardPayments(Long WebshopID) {
		int allCardPayments = 0;
		List<Payment> listOfPaymentsOfWebshop = Cache.payments.stream().filter(p -> p.getWebshopID().equals(WebshopID)
				&& p.getPaymentMethod() == PaymentMethod.CARD).collect(Collectors.toList());
		
		for(Payment p : listOfPaymentsOfWebshop) {
			allCardPayments += p.getAmount_HUF();
		}
		
		return allCardPayments;
	}
	
	public int getWebshopsTotalTransferPayments(Long WebshopID) {
		int allTransferPayments = 0;
		List<Payment> listOfPaymentsOfWebshop = Cache.payments.stream().filter(p -> p.getWebshopID().equals(WebshopID)
				&& p.getPaymentMethod() == PaymentMethod.TRANSFER).collect(Collectors.toList());
		
		for(Payment p : listOfPaymentsOfWebshop) {
			allTransferPayments += p.getAmount_HUF();
		}
		
		return allTransferPayments;
	}
	
}
