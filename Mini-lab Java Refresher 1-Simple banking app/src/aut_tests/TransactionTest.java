package aut_tests;

import java.sql.Date;

import model.transaction;
import utils.TestUtils;

public class TransactionTest {
	
	public static void main(String[] args) {
		testTransactionConstructors();
	}
	
	public static void testTransactionConstructors() {
		String strDate = "2003-01-02";
		Date date = Date.valueOf(strDate); 
		
		String test_account_number = "acc_num_31343";
		double test_transaction_amount = 1000.0;
		Date test_transaction_date = date;
		
		transaction testTransaction = new transaction(test_account_number, test_transaction_amount, test_transaction_date);
		
		System.out.println("Starting the assertions of the test method: testTransactionConstructors");
		
		String test_case_name = "TC1-getAccountnumber";
		if(testTransaction.getAccount_number() == test_account_number)
			TestUtils.printTestPassed(test_case_name);
		else
			TestUtils.printTestFailed(test_case_name);
		
		String test_case_name2 = "TC2-getTransactionamount";
		if(testTransaction.getTransaction_amount() == test_transaction_amount)
			TestUtils.printTestPassed(test_case_name2);
		else
				TestUtils.printTestFailed(test_case_name2);
		
		String test_case_name3 = "TC3-getTransactiondate";
		if(testTransaction.getTransaction_date() == test_transaction_date)
			TestUtils.printTestPassed(test_case_name3);
		else
			TestUtils.printTestFailed(test_case_name3);
			
	} 

}
