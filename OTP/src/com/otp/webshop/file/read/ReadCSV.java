package com.otp.webshop.file.read;

import java.io.*;

import java.time.*;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import com.otp.webshop.data.Cache;
import com.otp.webshop.entity.customer.Customer;
import com.otp.webshop.entity.payment.Payment;
import com.otp.webshop.entity.paymentmethod.PaymentMethod;
import com.otp.webshop.file.logger.MyLogger;


public class ReadCSV {
	
    private static final Logger appLogger = MyLogger.getLogger("src/data/application.log");
    private Cache cache = Cache.getInstance();
    
	@SuppressWarnings("resource")
	public void readCSVFile(String keyString, String fileName) {
		try {
	        String line = "";
	        String csvDelimiter = ",";
			
			BufferedReader br;
				br = new BufferedReader(new FileReader(fileName));

				br.readLine();
				
	            while ((line = br.readLine()) != null) {
	                String[] field = line.split(csvDelimiter);
	                
	                if(keyString.equals("customer")) {
	                    Customer customer = new Customer();
	                    
	                    customer.setWebshopID(Long.parseLong(field[0]));
	                    customer.setID(Long.parseLong(field[1]));
	                    customer.setName(field[2]);
	                    customer.setAddress(field[3]);
	                    
	                    try {
	                        Customer alreadyExistingCustomer = cache.getAllCustomerFromSameWebshop(customer.getWebshopID()).stream().filter(c -> c.getID().equals(customer.getID())).findFirst().get();
	                        appLogger.warning("A customer with this ID, in this Webshop already exists! " + alreadyExistingCustomer.toString());
	                    }
	                    
	                    catch (NoSuchElementException e){
	                    	if(customer.getName().matches(".*\\d+.*")) {
	                    		appLogger.warning("The customer's name can not contain numbers! " + customer.toString());
	                    	}
	                    	
	                    	else {
	                    		cache.addCustomer(customer);
	                    	}	
	                    }
	                }
	                
	                else if(keyString.equals("payments")) {
	                    Payment payment = new Payment();
	                    
	                    payment.setWebshopID(Long.parseLong(field[0]));
	                    payment.setClientID(Long.parseLong(field[1]));
	                    payment.setPaymentMethod(PaymentMethod.valueOf(field[2].toUpperCase()));
	                    payment.setAmount_HUF(Integer.parseInt(field[3]));
	                    
	                    if(payment.getPaymentMethod() == PaymentMethod.TRANSFER) {
	                    	payment.setCardNumber(null);
	                    	payment.setBankAccountNumber(field[4]);
	                    }
	                    
	                    else if(payment.getPaymentMethod() == PaymentMethod.CARD) {
	                    	payment.setCardNumber(field[5]);
	                    	payment.setBankAccountNumber(null);
	                    }
	                    
	                    payment.setDateOfPayment(LocalDate.parse(field[6]));
	                    
	                    try {	
	                        Payment alreadyExistingPayment = cache.getAllPaymentFromSameWebshop(payment.getWebshopID()).stream().filter(p -> p.getClientID().equals(payment.getClientID())).findFirst().get();
	                        appLogger.warning("A payment with this Client ID, in this Webshop already exists! " + alreadyExistingPayment.toString());
	                    }
	                    
	                    catch (NoSuchElementException e){
	                    	if(payment.getBankAccountNumber() == null && !payment.getCardNumber().matches("^(?=.{19}$)\\d{4}-\\d{4}-\\d{4}-\\d{4}$")) {
	                    		appLogger.warning("The card number is invalid! " + payment.toString());
	                    	}
	                    	
	                    	else if(payment.getCardNumber() == null && !payment.getBankAccountNumber().matches("^(?=.{26}$)\\d{8}-\\d{8}-\\d{8}$")) {
	                    		appLogger.warning("The bank account number is invalid! " + payment.toString());
	                    	}
	                    	
	                    	else {
	                    		cache.addPayment(payment);
	                    	}
	                    }
	                }
	            }
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			appLogger.warning("File does not exist!");
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			appLogger.warning("Please fill out all the required fields!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
