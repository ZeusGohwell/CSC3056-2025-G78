package aut_tests;

import model.User;
import utils.TestUtils;

public class UserTest {

    public static void main(String[] args) {
        testUserConstructor();
    }

    public static void testUserConstructor() {
        String test_username = "mike";
        String test_password = "my_passwd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";

        User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);

        
        System.out.println("starting an assesrtion of the test method: testuserConstruction");

        String test_case_name = "TC1-getUsername";
        if (testUser.getUsername() == test_username)
            TestUtils.printTestPassed(test_case_name);
        else 
            TestUtils.printTestFailed(test_case_name);
        
        String test_case_name2 = "TC1-getPassword";
        if (testUser.getPassword() == test_password)
            TestUtils.printTestPassed(test_case_name2);
        else 
            TestUtils.printTestFailed(test_case_name2);
        
        String test_case_name3 = "TC1-getFirstName";
        if (testUser.getFirst_name() == test_first_name)
            TestUtils.printTestPassed(test_case_name3);
        else 
            TestUtils.printTestFailed(test_case_name3);
        
        String test_case_name4 = "TC1-getLastName";
        if (testUser.getLast_name() == test_last_name)
            TestUtils.printTestPassed(test_case_name4);
        else 
            TestUtils.printTestFailed(test_case_name4);
        
        String test_case_name5 = "TC1-getMobileNumber";
        if (testUser.getMobile_number() == test_mobile_number)
            TestUtils.printTestPassed(test_case_name5);
        else 
            TestUtils.printTestFailed(test_case_name5);
        
        assert testUser.getUsername() == test_username;
        System.out.println("all java assertions in the test suite passed(none failed)");

    }
}
