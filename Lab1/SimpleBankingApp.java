package app;

import controller.AccountController;
import controller.UserController;
import java.util.Scanner;
import java.util.Vector;
import model.account;
import model.transaction;
import model.User;

public class SimpleBankingApp {
    public static Vector<User> users = new Vector<User>();
    public static Vector<account> accounts  = new Vector<account>();
    public static Vector<transaction> transactions =  new Vector<transaction>();

  
    //////////////////////////////////////////////////////
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	
    	UserController.loadUserData(users);
        // let's print them all to see if they have been loaded (populated) properly
    	UserController.printAllUsers(users);

    	AccountController.loadAccountData(accounts);
        // let's print them all to see if they have been loaded (populated) properly
        System.out.println("Accounts: initial state, after loading...");
        AccountController.printAllAccounts(accounts, transactions);

        // let's do some activities on the populated accounts, add transactions, etc.
        // Deposit: adding a transaction with a positive value
        // Withdraw: adding a transaction with a negative value
        AccountController.addTransaction("5495-1234", 0.0, transactions);
        System.out.println("Account: after the 1st addTransaction function call...");
        AccountController.printAllAccounts(accounts, transactions);

        // and some more activities on the accounts
        AccountController.addTransaction("5495-1234", 520.00, transactions);
        AccountController.addTransaction("9999-1111", 21.00, transactions); // it seems this account does not exist in the loaded (populated) data, 
        // but the addTransaction does not do that check, need to improve that function in future
        // let's print the accounts and their balance to see if the above transaction have impacted their balances
        System.out.println("Account: after the 2nd/3rd addTransaction function calls...");
        AccountController.printAllAccounts(accounts, transactions);
        
        System.out.print("Enter username to check total balance: ");
        String username = scanner.nextLine(); 

        try {
            double balance = UserController.getTotalBalance(username, accounts, transactions);
            System.out.println("Total balance for " + username + ": $" + balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();  
    }
}