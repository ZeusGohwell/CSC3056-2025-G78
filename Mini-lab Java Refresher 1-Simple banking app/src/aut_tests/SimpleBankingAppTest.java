package aut_tests;

import java.util.List;
import app.SimpleBankingApp;
import utils.TestUtils;
import controller.AccountController;
import controller.UserController;
import model.transaction;
import model.account;

public class SimpleBankingAppTest {

    // system under test (SUT):
    // static SimpleBankingApp mainApp = new SimpleBankingApp();

    // this test method (test case) verifies if the data load feature of the class (unit or component) 
    // under test (UUT) works properly
    public static void testLoadData() {
        // 1-Setup phase: none

        // 2-Exercise phase
        // Loading user and account data through the respective controllers
        UserController.loadUserData(SimpleBankingApp.users);
        AccountController.loadAccountData(SimpleBankingApp.accounts);

        // 3-Verify phase
        // Verify if users and accounts have been loaded properly
        if (SimpleBankingApp.users.size() == 3)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadUserData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadUserData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (SimpleBankingApp.accounts.size() == 4)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadAccountData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadAccountData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        // 4-Teardown phase: no need for teardown as we want to keep users and accounts for other tests
    }

    // this test method (test case) verifies if the Deposit feature works properly
    public static void testDeposits() {
        // 1-Setup phase
        List<transaction> transactions = SimpleBankingApp.transactions;
        double balanceBefore = AccountController.getBalance("5495-1234", transactions); 
        double depositAmount = 50.21;

        // 2-Exercise phase
        AccountController.addTransaction("5495-1234", depositAmount, transactions);
        double balanceAfter = AccountController.getBalance("5495-1234", transactions);

        // 3-Verify
        assert balanceBefore + depositAmount == balanceAfter;
        if (balanceBefore + depositAmount == balanceAfter)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testDeposits: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testDeposits: TC1 FAILED XXX: balanceBefore + depositAmount != balanceAfter");
            System.out.format("testDeposits: balanceBefore = %.2f ; depositAmount = %.2f ; balanceAfter = %.2f %s\n", 
                    balanceBefore , depositAmount , balanceAfter, TestUtils.TEXT_COLOR_RESET);
        }

        // 4-tear-down: undo the transaction to restore the balance
        AccountController.addTransaction("5495-1234", -depositAmount, transactions);
    }

    // This test method verifies if the Withdrawal feature works properly
    public static void testWithdrawals() {
        // 1-Setup phase
        List<transaction> transactions = SimpleBankingApp.transactions;
        double balanceBefore = AccountController.getBalance("5495-1234", transactions); 
        double withdrawalAmount = 30.50;

        // 2-Exercise phase
        AccountController.addTransaction("5495-1234", -withdrawalAmount, transactions);
        double balanceAfter = AccountController.getBalance("5495-1234", transactions);

        // 3-Verify
        assert balanceBefore - withdrawalAmount == balanceAfter;
        if (balanceBefore - withdrawalAmount == balanceAfter) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testWithdrawals: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testWithdrawals: TC1 FAILED XXX: balanceBefore - withdrawalAmount != balanceAfter");
            System.out.format("testWithdrawals: balanceBefore = %.2f ; withdrawalAmount = %.2f ; balanceAfter = %.2f %s\n", 
                    balanceBefore , withdrawalAmount , balanceAfter, TestUtils.TEXT_COLOR_RESET);
        }

        // 4-Tear-down: undo the withdrawal transaction
        AccountController.addTransaction("5495-1234", withdrawalAmount, transactions);
    }
    
    public static void testGetTotalBalance() {
        List<transaction> transactions = SimpleBankingApp.transactions;
        String testUsername = "mike";  

        double expectedTotalBalance = 0.0;
        boolean userHasAccounts = false;

    
        for (account acc : SimpleBankingApp.accounts) {
            if (acc.getUsername_of_account_holder().equals(testUsername)) {
                userHasAccounts = true;
                expectedTotalBalance += AccountController.getBalance(acc.getAccount_number(), transactions);
            }
        }

        try {
            double actualTotalBalance = UserController.getTotalBalance(testUsername, SimpleBankingApp.accounts, transactions);

            if (expectedTotalBalance == actualTotalBalance) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "testGetTotalBalance: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "testGetTotalBalance: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
                System.out.format("Expected: %.2f, but got: %.2f\n", expectedTotalBalance, actualTotalBalance);
            }
        } catch (IllegalArgumentException e) {
            if (!userHasAccounts) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "testGetTotalBalance: TC2 passed (No accounts found for user)" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "testGetTotalBalance: TC2 FAILED (Unexpected exception)" + TestUtils.TEXT_COLOR_RESET);
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void testAddTransaction_InvalidAmount() {
        List<transaction> transactions = SimpleBankingApp.transactions;
        String accountNumber = "5495-1234";
        int initialSize = transactions.size();  
        
        try {
            AccountController.addTransaction(accountNumber, 0.00, transactions);
            
            System.out.println(TestUtils.TEXT_COLOR_RED + "testAddTransaction_InvalidAmount: TC1 FAILED (should have thrown exception for zero amount)" + TestUtils.TEXT_COLOR_RESET);
        } catch (IllegalArgumentException e) {
            
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testAddTransaction_InvalidAmount: TC1 passed (exception thrown for zero amount)" + TestUtils.TEXT_COLOR_RESET);
        }

     
        if (transactions.size() == initialSize) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testAddTransaction_InvalidAmount: TC2 passed (transaction list not altered by invalid transaction)" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testAddTransaction_InvalidAmount: TC2 FAILED (transaction list altered by invalid transaction)" + TestUtils.TEXT_COLOR_RESET);
        }
    }



    public static void main(String[] args) {
        // Run the test cases
        testLoadData();
        testDeposits();
        testWithdrawals();
        testGetTotalBalance();
        testAddTransaction_InvalidAmount();
    }

}
