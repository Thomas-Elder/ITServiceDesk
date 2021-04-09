
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Create list for accounts
        ArrayList <Account> accountList= new ArrayList<Account>(); 

        // Create hardcoded technician accounts
        accountList.add(new Technician("harry.style@cinco.com", "Harry Style", "8992 6321", "1234", 1));
        accountList.add(new Technician("niall.horan@cinco.com", "Niall Horan", "8992 6322", "5678", 1));
        accountList.add(new Technician("liam.payne@cinco.com", "Liam Payne", "8992 6323", "9123", 1));
        accountList.add(new Technician("louis.tomlinson@cinco.com", "Louis Tomlinson", "8992 6324", "4567", 2));
        accountList.add(new Technician("zayn.malik@cinco.com", "Zayn Malik", "8992 6325", "8912", 2));

        // Create hardcoded test Staff Account
        accountList.add(new Staff("jane.doe@cinco.com", "Jane Doe", "8992 1234", "1234"));

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
