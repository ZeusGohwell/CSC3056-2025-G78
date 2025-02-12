package aut_tests;

import java.sql.Date;

import model.account;
import utils.TestUtils;

public class AccountTest {
	
	public static void main(String[] args) {
		testAccountConstructors();
	}
	
	public static void testAccountConstructors() {
		String strDate = "2003-01-02";
		Date date = Date.valueOf(strDate); 
		
		String test_account_number = "acc_num";
		String test_username_of_account_holder = "ryan";
		String test_account_type = "Saving ";
		Date test_account_opening_date = date;
		
		account testAccount = new account(test_account_number, test_username_of_account_holder, test_account_type, test_account_opening_date);
		
		System.out.println("Starting the assertions of the test method: testAccountConstructors");
		
		String test_case_name = "TC1-getAccountnumber";
		if(testAccount.getAccount_number() == test_account_number)
			TestUtils.printTestPassed(test_case_name);
		else
			TestUtils.printTestFailed(test_case_name);
		
		String test_case_name2 = "TC2-getUsernameofaccountholder";
		if(testAccount.getUsername_of_account_holder() == test_username_of_account_holder)
			TestUtils.printTestPassed(test_case_name2);
		else
			TestUtils.printTestFailed(test_case_name2);
		
		String test_case_name3 = "TC3-getAccounttype";
		if(testAccount.getAccount_type() == test_account_type)
			TestUtils.printTestPassed(test_case_name3);
		else
			TestUtils.printTestFailed(test_case_name3);
		
		String test_case_name4 = "TC4-Accountopeningdate";
		if(testAccount.getAccount_opening_date() == test_account_opening_date)
			TestUtils.printTestPassed(test_case_name4);
			else
			TestUtils.printTestFailed(test_case_name4);
	}

}
