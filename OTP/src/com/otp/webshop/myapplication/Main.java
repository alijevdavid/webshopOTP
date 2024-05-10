package com.otp.webshop.myapplication;

import com.otp.webshop.file.logger.MyLogger;
import com.otp.webshop.file.read.ReadCSV;
import com.otp.webshop.file.write.WriteCSV;

public class Main {
	public static void main(String[] args) {
		ReadCSV reader = new ReadCSV();
		WriteCSV writer = new WriteCSV();
		
		reader.readCSVFile("customer", "src/data/customer.csv");
		reader.readCSVFile("payments", "src/data/payments.csv");
		
		writer.customersAllPayments();
		writer.customersTop2Payments();
		writer.webshopsTotalIncome();
		
        MyLogger.closeLoggers();
	}
}
