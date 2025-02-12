package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

public class account {
	String account_number;
	String username_of_account_holder;
	String account_type;
	Date account_opening_date;
	
	public account(String account_number, String username_of_account_holder, String account_type, Date date) {
		this.account_number = account_number;
	    this.username_of_account_holder = username_of_account_holder;
	    this.account_type = account_type;
	    this.account_opening_date = date;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getUsername_of_account_holder() {
		return username_of_account_holder;
	}
	public void setUsername_of_account_holder(String username_of_account_holder) {
		this.username_of_account_holder = username_of_account_holder;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public Date getAccount_opening_date() {
		return account_opening_date;
	}
	public void setAccount_opening_date(Date account_opening_date) {
		this.account_opening_date = account_opening_date;
	}
	
	    public String toString() {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        // Ensure account_opening_date is not null before formatting
	        String formattedDate = (account_opening_date != null) ? sdf.format(account_opening_date) : "N/A";

	        return "Account Number: " + account_number +
	               "\nAccount Holder: " + username_of_account_holder +
	               "\nAccount Type: " + account_type +
	               "\nOpening Date: " + formattedDate;
	    }

}
