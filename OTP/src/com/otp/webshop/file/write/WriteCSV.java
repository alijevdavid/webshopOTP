package com.otp.webshop.file.write;

import java.io.FileWriter;

import java.io.IOException;
import java.io.File;
import java.util.List;

import com.otp.webshop.data.Cache;
import com.otp.webshop.entity.customer.Customer;

public class WriteCSV {
	
	private Cache cache = Cache.getInstance();
	
	public void customersAllPayments() {
		try {
	        String csvFile = "src/data/report01.csv";
	        
	        File file = new File(csvFile);
	        if (!file.exists()) {
	            file.createNewFile();
	        }

        	FileWriter writer = new FileWriter(csvFile);
            writer.append("Name,Address,Total payments\n");
            
            List<Customer> allCustomer = cache.getAllCustomers();
            
            for(Customer c : allCustomer) {
            	writer.append(c.getName() + "," + c.getAddress() + "," + Integer.toString(cache.getCustomersTotalSpending(c.getID())) + "\n");
            }
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void customersTop2Payments() {
        try {
	        String csvFile = "src/data/top.csv";
	        
	        File file = new File(csvFile);
	        if (!file.exists()) {
	            file.createNewFile();
	        }     

        	FileWriter writer = new FileWriter(csvFile);
            writer.append("Name,Address,Total payments\n");
            
            List<Customer> allCustomer = cache.getAllCustomers();
            
            int top1 = Integer.MIN_VALUE;
            int top2 = Integer.MIN_VALUE;
            Customer top1Customer = null;
            Customer top2Customer = null;
            
            for(Customer c : allCustomer) {
            	int totalSpending = cache.getCustomersTotalSpending(c.getID());
                if(totalSpending > top1) {
                	top2 = top1;
                    top1 = totalSpending;
                    top2Customer = top1Customer;
                    top1Customer = c;
                } else if (totalSpending > top2) {
                	top2 = totalSpending;
                	top2Customer = c;
                }
            }
            
        	writer.append(top1Customer.getName() + "," + top1Customer.getAddress() + "," + Integer.toString(top1) + "\n");
        	writer.append(top2Customer.getName() + "," + top2Customer.getAddress() + "," + Integer.toString(top2) + "\n");
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void webshopsTotalIncome() {
        try {
	        String csvFile = "src/data/report02.csv";
	        
	        File file = new File(csvFile);
	        if (!file.exists()) {
	            file.createNewFile();
	        }
        
        	FileWriter writer = new FileWriter(csvFile);
        	
            writer.append("Webshop,Total Card Payments,Total transfers\n");
            
            writer.append("1," + cache.getWebshopsTotalCardPayments((long)1) + "," + cache.getWebshopsTotalTransferPayments((long)1) + "\n");
            writer.append("2," + cache.getWebshopsTotalCardPayments((long)2) + "," + cache.getWebshopsTotalTransferPayments((long)2));
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
