
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcom to Cinco IT Service Desk!");

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
