package man_tests;

import java.sql.Date;

import model.transaction;


public class transactionTest {
	public static void main(String[] args) {
		String strDate = "2024-05-13";
		Date date = Date.valueOf(strDate);
		
		transaction testTransaction = new transaction("40392304", 0.0, date);
		
		System.out.println(testTransaction);
	}


}
  