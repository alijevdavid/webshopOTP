package com.otp.webshop.formatter;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyFormatter {
	
	public String fromIntToCurrency(String countryCode, String currencyCode, int intToFormat) {
    	Currency c = Currency.getInstance(currencyCode);
    	
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale(countryCode, currencyCode)); 
        formatter.setCurrency(c);
        formatter.setMaximumFractionDigits(0);

        String currency = formatter.format(intToFormat);
        
        return currency;
	}
}
