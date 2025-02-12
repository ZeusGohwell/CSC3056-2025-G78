package controller;

import model.account;
import model.transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class AccountController{
	
	 public static double getBalance(String account_number, Vector<transaction> transactions) {
	        double balance = 0.0;
	        for (transaction t : transactions) {
	            if (t.getAccount_number().equals(account_number)) {
	                balance += t.getTransaction_amount();
	            }
	        }
	        return balance;
	    }
	 
	  public static void loadAccountData(List<account> accounts) {
	        account anAccount;
	        try {
	            anAccount = new account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
	            accounts.add(anAccount);

	            anAccount = new account("5495-1239", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020"));
	            accounts.add(anAccount);

	            anAccount = new account("5495-1291", "mike", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019"));
	            accounts.add(anAccount);

	            anAccount = new account("5495-6789", "David.McDonald@gmail.com", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
	            accounts.add(anAccount);

	        } catch (ParseException e) {            
	            e.printStackTrace();
	        }  
	    }

	    // Method to print all accounts
	    public static void printAllAccounts(List<account> accounts, List<transaction> transactions) {
	        System.out.println("There are: " + accounts.size() + " accounts in the system.");

	        System.out.println(String.format("%-10s| %-30s| %-10s| %-15s| %-15s", 
	                "Account #", "username_of_account_holder", "type", "opening_date", "Balance"));
	        System.out.println("--------------------------------------------------------------------------------");

	        for (account account : accounts) {
	            // Call the getBalance method to get the balance for each account
	            double balance = getBalance(account.getAccount_number(), transactions);
	            System.out.printf("%-10s| %-30s| %-10s| %-15s| %-15s%n",
	                    account.getAccount_number(), account.getUsername_of_account_holder(), account.getAccount_type(), account.getAccount_opening_date(), balance);
	        }
	        System.out.println();
	    }

	    // Method to calculate the balance of a given account (by its number)
	    public static double getBalance(String account_number, List<transaction> transactions) {
	        double balance = 0.0;
	        boolean accountExists = false;  // To track if any transaction matches the account_number
	        
	        for (transaction t : transactions) {
	            if (t.getAccount_number().equals(account_number)) {
	                accountExists = true;
	                balance += t.getTransaction_amount();
	            }
	        }
	        
	        // If no transactions found for the account, throw an exception
	      // if (!accountExists) {
	       //  throw new IllegalArgumentException("Account number " + account_number + " does not exist.");
	     //   }

	        return balance;
	    }

	    public static void addTransaction(String account_number, double amount, List<transaction> transactions) { 
	    	
	    	if (amount == 0) {
	    		throw new IllegalArgumentException("Transaction amount cannot be zero.");
	    	}
	        transaction aTransaction =  new transaction(account_number, amount, Calendar.getInstance().getTime());
	        transactions.add(aTransaction);
	    }
}