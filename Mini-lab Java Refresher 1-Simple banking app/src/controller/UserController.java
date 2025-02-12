package controller;

import model.User;
import java.util.Vector;
import model.account;
import model.transaction;
import java.util.List;
import controller.AccountController;
import java.util.Scanner;


public class UserController {
	
    public static void loadUserData(Vector<User> users) {
        User aUser = new User("mike", "my_passwd", "Mike", "Smith", "07771234567");
        users.add(aUser);

        aUser = new User("james.cameron@gmail.com", "angel", "James", "Cameron",  "07777654321");
        users.add(aUser);

        aUser = new User("julia.roberts@gmail.com", "change_me",   "Julia", "roberts",   "07770123456");
        users.add(aUser); 
    }

    public static void printAllUsers(Vector<User> users) {
        System.out.println("There are: " + users.size() + " users in the system."); 


        System.out.printf("%-25s| %-15s| %-15s| %-15s| %-15s%n", 
                "Username", "Password", "First Name", "Last Name", "Mobile Number");
        System.out.println("-------------------------------------------------------------------------------------------");


        for (User user : users) {
            System.out.printf("%-25s| %-15s| %-15s| %-15s| %-15s%n",
                    user.getUsername(), user.getPassword(), user.getFirst_name(), user.getFirst_name(), user.getMobile_number());
        }

        System.out.println();
    }
    
    public static double getTotalBalance(String username, Vector<account> accounts,  List<transaction> transactions) {
    	double totalBalance = 0.0;
    	boolean user_has_accounts = false;
    	
    	for (account account : accounts) {
    		if (account.getUsername_of_account_holder().equals(username)) {
    			user_has_accounts = true;
    			totalBalance += AccountController.getBalance(account.getAccount_number(), transactions);
    		}
    	}
    if (!user_has_accounts) {
    	throw new IllegalArgumentException("User"+ username +"does not have any accounts");
    }
    
    return totalBalance;
    }
    
   



}
