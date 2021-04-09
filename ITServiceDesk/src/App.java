
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Create list for accounts
        ArrayList <Account> accountList= new ArrayList<Account>(); 

        // Create technician accounts
        accountList.add(new Account("", "Harry Style", "", ""));
        accountList.add(new Account("", "Niall Horan", "", ""));
        accountList.add(new Account("", "Liam Payne", "", ""));
        accountList.add(new Account("", "Louis Tomlinson", "", ""));
        accountList.add(new Account("", "Zayn Malik", "", ""));

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to Cinco IT Service Desk!");

        char option = '0';

        while (option != 'X') {

            // TODO print action options
            System.out.println("Please select and option:");
            System.out.println("1 - Create an Account");
            System.out.println("2 - Log in");
            System.out.println("3 - Create a Ticket");
            System.out.println("X - Exit");

            // Get input
            // TODO Wrap this to validate input
            option = sc.nextLine().toCharArray()[0];
            
            // TODO switch to handle option selection
        }
        
        sc.close();
    }
}
