
import java.util.*;

/**
 * <h1>Cinco IT Service Desk Application</h1>
 * 
 * A simple application for managing tickets for IT tasks.
 */
public class App {

    static Scanner sc;
    static Database db;
    static Boolean loggedIn;

    /**
     * <h2>Main</h2>
     * This is the entry point for the application. 
     * 
     * @param not used
     * @return none
     */
    public static void main(String[] args) throws Exception {
       
        init();
        interactionLoop();
        end();
        
    }

    /**
     * <h2>Init</h2>
     * Initialise required fields and hardcoded values. 
     * 
     * Initialises the Database, then adds the hardcoded Accounts.
     * Initialises the Scanner used for getting input from the user. 
     * Initialises the loggedIn field, used for tracking if a user is logged in.
     * 
     * @param none
     * @return none
     */
    public static void init(){

        // Initialise Database
        db = new Database();

        // Create hardcoded technician accounts
        db.addAccount(new Technician("harry.style@cinco.com", "Harry Style", "8992 6321", "1234", 1));
        db.addAccount(new Technician("niall.horan@cinco.com", "Niall Horan", "8992 6322", "5678", 1));
        db.addAccount(new Technician("liam.payne@cinco.com", "Liam Payne", "8992 6323", "9123", 1));
        db.addAccount(new Technician("louis.tomlinson@cinco.com", "Louis Tomlinson", "8992 6324", "4567", 2));
        db.addAccount(new Technician("zayn.malik@cinco.com", "Zayn Malik", "8992 6325", "8912", 2));

        // Create hardcoded test Staff Account
        db.addAccount(new Staff("jane.doe@cinco.com", "Jane Doe", "8992 1234", "1234"));

        // Initialise Scanner for input
        sc = new Scanner(System.in);

        // Initialise loggedIn to false
        loggedIn = false;
    }

    /**
     * <h2>Interaction Loop</h2>
     * Runs the main interaction loop.
     * 
     * Displays the options available to the user, based on whether they're logged in or not, 
     * until they enter X to exit the application.
     * 
     * @param none
     * @return none
     */
    public static void interactionLoop(){
        System.out.println("Welcome to Cinco IT Service Desk!");

        char option = '0';

        while (option != 'X') {
            
            // If the user is not loggedIn, create/log in.
            if (!loggedIn){

                // Print action options
                System.out.println();
                System.out.println("Please select an option:");
                System.out.println("1 - Create an Account");
                System.out.println("2 - Log in");
                System.out.println("X - Exit");

                // Get input
                option = sc.nextLine().toCharArray()[0];

                switch (option){
                    case '1':
                        createAccount();
                        break;
                    case '2':
                        logIn();
                        break;
                    case 'X':
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                }

            // If the user is loggedIn, create/view Tickets.
            } else {

                System.out.println();
                System.out.println("Please select an option:");
                System.out.println("1 - Create a Ticket");
                System.out.println("X - Exit");

                // Get input
                option = sc.nextLine().toCharArray()[0];

                switch (option){
                    case '1':
                        createTicket();
                        break;
                    case 'X':
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                }
            }
        }
    }

    /**
     * <h2>End</h2>
     * Clean up before ending execution. 
     * 
     * Closes any open resources, which at this point is just the Scanner.
     * 
     * @param none
     * @return none
     */
    public static void end(){
        sc.close();
    }

    /**
     * <h2>Create Account</h2>
     * Prompts user for details in order to create a new Account.
     * 
     * First prompts for the email address to use, and checks if this is already in the database. If
     * it is, the function returns.
     * 
     * Otherwise, prompts for name, number and password, and uses the responses to create a new 
     * Account and add it to the database.
     * 
     * @param none
     * @return none
     */
    public static void createAccount(){
        System.out.println("Please enter your email address:");
        String email = sc.nextLine();

        Account account = db.getAccount(email);

        // Check if the db contains an Account with this email address
        if (account != null) {
            System.out.println("Email already in use. Please use a different address.");

        // Otherwise, continue with Account creation
        } else {
            String name, number, password;

            System.out.println("Please enter your name:");
            name = sc.nextLine();

            System.out.println("Please enter your number:");
            number = sc.nextLine();

            System.out.println("Please enter your password:");
            password = sc.nextLine();

            // Add new Account to the db
            db.addAccount(new Staff(email, name, number, password));
        }
    }

    /**
     * <h2>Log In</h2>
     * Prompts the user for log in details.
     * 
     * First prompts for an email address, and checks if it is in the database. If it is not, 
     * the function returns.
     * 
     * If it is in the database, the user is prompted for the password. If this matches the 
     * value stored in the database, print Success and set loggedIn to true.
     * 
     * @param none
     * @return none
     */
    public static void logIn(){
        System.out.println("Please enter your email address:");
        String email = sc.nextLine();

        Account account = db.getAccount(email);

        if (account == null) {
            System.out.println("Email not found, please check the address and try again.");
        } else {
            System.out.println("Please enter your password:");
            String password = sc.nextLine();

            if (password.equals(account.password)) {
                System.out.println("Success!");
                loggedIn = true;
            }
        }
    }

    /**
     * <h2>Create Ticket</h2>
     * Prompts the user to enter details to create a new Ticket.
     * 
     * @param none
     * @return none
     */
    public static void createTicket(){

        if (!loggedIn){
            System.out.println("You must be logged in to create a ticket!");
        } else {
            System.out.println("You can create a ticket!");
        }
    }
}
