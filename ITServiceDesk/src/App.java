
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Create list for accounts
        ArrayList <Account> accountList= new ArrayList<Account>(); 

        // Create technician accounts
        accountList.add(new Technician("", "Harry Style", "", "", 1));
        accountList.add(new Technician("", "Niall Horan", "", "", 1));
        accountList.add(new Technician("", "Liam Payne", "", "", 1));
        accountList.add(new Technician("", "Louis Tomlinson", "", "", 2));
        accountList.add(new Technician("", "Zayn Malik", "", "", 2));

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
