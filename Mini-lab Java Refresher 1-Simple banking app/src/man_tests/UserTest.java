package man_tests;
import model.User;

public class UserTest {
	
	public static void main(String[] args) {
		User testUser = new User("Mike","my_passwrd", "Mike", "Smith", "077172839");
		
		System.out.println(testUser);
	}

}
