
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Create list for accounts
        ArrayList <Account> accountList= new ArrayList<Account>(); 

        // Create technician accounts
        accountList.add(new Technician("harry.style@cinco.com.au", "Harry Style", "", "", 1));
        accountList.add(new Technician("niall.horan@cinco.com.au", "Niall Horan", "", "", 1));
        accountList.add(new Technician("liam.payne@cinco.com.au", "Liam Payne", "", "", 1));
        accountList.add(new Technician("louis.tomlinson@cinco.com.au", "Louis Tomlinson", "", "", 2));
        accountList.add(new Technician("zayn.malik@cinco.com.au", "Zayn Malik", "", "", 2));

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
