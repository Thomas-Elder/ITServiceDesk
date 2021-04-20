
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <h1>Cinco IT Service Desk Application</h1> A simple application for managing
 * tickets for IT tasks.
 */
public class App {

	static Scanner sc;
	static Database db;
	static Boolean loggedIn;
	static Account user;

	/**
	 * <h2>Main</h2> This is the entry point for the application.
	 * 
	 * @param not used
	 * @return none
	 */
	public static void main(String[] args) {

		init();
		interactionLoop();
		end();

	}

	/**
	 * <h2>Init</h2> Initialise required fields and hardcoded values.
	 * 
	 * Initialises the Database, then adds the hardcoded Technician and Staff Accounts.
	 * Creates hardcoded test Tickets and adds them to the Database.
	 * Initialises the Scanner used for getting input from the user. 
	 * Initialises the loggedIn field, used for tracking if a user is logged in.
	 * 
	 * @param none
	 * @return none
	 */
	public static void init() {

		// Initialise Database
		db = new Database();

		// Create hardcoded technician accounts
		db.addTechnicianAccount(new Technician("harry.style@cinco.com", "Harry Style", "8992 6321", "1234", 1));
		db.addTechnicianAccount(new Technician("niall.horan@cinco.com", "Niall Horan", "8992 6322", "5678", 1));
		db.addTechnicianAccount(new Technician("liam.payne@cinco.com", "Liam Payne", "8992 6323", "9123", 1));
		db.addTechnicianAccount(new Technician("louis.tomlinson@cinco.com", "Louis Tomlinson", "8992 6324", "4567", 2));
		db.addTechnicianAccount(new Technician("zayn.malik@cinco.com", "Zayn Malik", "8992 6325", "8912", 2));

		// Create hardcoded test Staff Accounts
		Staff jane = new Staff("jane.doe@cinco.com", "Jane Doe", "8992 1234", "1234");
		Staff jon = new Staff("jon.doe@cinco.com", "Jon Doe", "8992 1234", "1234");
		db.addStaffAccount(jane);
		db.addStaffAccount(jon);

		// Create test Ticket for testing
		ITSystem itSystem = new ITSystem("OS", "program", "version");

		List<Ticket> testTickets = new ArrayList<Ticket>();
		testTickets.add(new Ticket(jane, "Test 0", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jane, "Test 1", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 2", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 3", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jon, "Test 4", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jon, "Test 5", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jane, "Test 6", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jane, "Test 7", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 8", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 9", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jane, "Test 10", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 11", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 12", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jane, "Test 13", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jane, "Test 14", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 15", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 16", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jon, "Test 17", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jon, "Test 18", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jane, "Test 19", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jane, "Test 20", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 21", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 22", Ticket.Status.open, Ticket.Severity.low, itSystem));
		testTickets.add(new Ticket(jane, "Test 23", Ticket.Status.open, Ticket.Severity.medium, itSystem));
		testTickets.add(new Ticket(jane, "Test 24", Ticket.Status.open, Ticket.Severity.high, itSystem));
		testTickets.add(new Ticket(jon, "Test 25", Ticket.Status.open, Ticket.Severity.low, itSystem));

		// Change dates on some tickets so they're older
		try {
			testTickets.get(0).creationDate = LocalDate.parse("2021-03-10");
			testTickets.get(5).creationDate = LocalDate.parse("2021-03-10");
			testTickets.get(3).creationDate = LocalDate.parse("2021-04-12");
			testTickets.get(6).creationDate = LocalDate.parse("2021-04-14");
		} catch (Exception e) {
			System.err.println(e);
		}

		// Change some status to test the report properly.
		testTickets.get(3).updateStatus(Ticket.Status.closed);
		testTickets.get(6).updateStatus(Ticket.Status.archived);

		// Add all tickets to the db
		for (Ticket ticket : testTickets){
			db.addTicket(ticket);
		}

		// Initialise Scanner for input
		sc = new Scanner(System.in);

		// Initialise loggedIn to false
		loggedIn = false;
	}

	/**
	 * <h2>Interaction Loop</h2> Runs the main interaction loop.
	 * 
	 * Displays the options available to the user, based on whether they're logged
	 * in or not, until they enter X to exit the application.
	 * 
	 * @param none
	 * @return none
	 */
	public static void interactionLoop() {
		System.out.println();
		System.out.println("Welcome to Cinco IT Service Desk!");

		char option = '0';

		while (option != 'X' && option != 'x') {

			// If the user is not loggedIn, create/log in.
			if (!loggedIn) {

				// Print action options
				System.out.println();
				System.out.println("Please select an option:");
				System.out.println("1 - Create an Account");
				System.out.println("2 - Staff Log in");
				System.out.println("3 - Technician Log in");
				System.out.println("X - Exit");

				// Get input
				option = sc.nextLine().toCharArray()[0];

				switch (option) {
				case '1':
					createStaffAccount();
					break;
				case '2':
					staffLogIn();
					break;
				case '3':
					technicianLogIn();
					break;
				case 'X':
				case 'x':
					System.out.println("Thanks for using the Cinco IT Service Desk!");
					break;
				default:
					System.out.println("Please select a valid option.");
				}

			// If the user is loggedIn, create/view Tickets.
			} else {

				// If the user is Technician...
				if (user.technician) {

					// Print Technician action options
					System.out.println();
					System.out.println("Please select an option:");
					System.out.println("1 - View Full Ticket list");
					System.out.println("2 - View My Ticket list");
					System.out.println("3 - Amend severity of a ticket");
					System.out.println("4 - Amend status of a ticket");
					System.out.println("5 - View Ticket report");
					System.out.println("X - Exit");

					// Get input
					option = sc.nextLine().toCharArray()[0];

					switch (option) {
					case '1':
						printAllTickets();
						break;
					case '2':
						printMyTickets((Technician) user);
						break;
					case '3': // Amend severity
						updateTicketSeverity();
						break;
					case '4': // Amend status
						updateTicketStatus();
						break;
					case '5':
						printReport();
						break;
					case 'X':
					case 'x':
						System.out.println("\nThanks for using the Cinco IT Service Desk!\n");
						break;
					default:
						System.out.println("Please select a valid option.");
					}

					// Else it's a Staff Account
				} else {

					// Print Staff action options
					System.out.println();
					System.out.println("Please select an option:");
					System.out.println("1 - Create a Ticket");
					System.out.println("2 - View my Ticket list");
					System.out.println("X - Exit");

					// Get input
					option = sc.nextLine().toCharArray()[0];

					switch (option) {
					case '1':
						createTicket();
						break;
					case '2':
						printMyTickets((Staff) user);
						break;
					case 'X':
					case 'x':
						System.out.println("Thanks for using the Cinco IT Service Desk!");
						System.out.println();
						break;
					default:
						System.out.println("Please select a valid option.");
					}
				}
			}
		}
	}

	/**
	 * <h2>End</h2> Clean up before ending execution.
	 * 
	 * Closes any open resources, which at this point is just the Scanner.
	 * 
	 * @param none
	 * @return none
	 */
	public static void end() {
		sc.close();
	}

	/**
	 * <h2>Create Account</h2> Prompts user for details in order to create a new
	 * Account.
	 * 
	 * First prompts for the email address to use, and checks if this is already in
	 * the database. If it is, the function returns.
	 * 
	 * Otherwise, prompts for name, number and password, and uses the responses to
	 * create a new Account and add it to the database.
	 * 
	 * @param none
	 * @return none
	 */
	public static void createStaffAccount() {

		System.out.println("\nPlease enter your email address:");
		String email = sc.nextLine();

		Account account = db.getStaffAccount(email);

		// Check if the db contains an Account with this email address
		if (account != null) {
			System.out.println("\nEmail already in use. Please use a different address.");

			// Otherwise, continue with Account creation
		} else {
			String name, number, password;

			System.out.println("\nPlease enter your name:");
			name = sc.nextLine();

			System.out.println("\nPlease enter your number:");
			number = sc.nextLine();

			System.out.println("\nPlease enter your password:");
			password = sc.nextLine();

			// Add new Account to the db
			db.addStaffAccount(new Staff(email, name, number, password));

			System.out.println("\nAccount successfully created!");
			System.out.println();
		}
	}

	/**
	 * <h2>Staff Log In</h2> Prompts the user for log in details.
	 * 
	 * First prompts for an email address, and checks if it is in the database. If
	 * it is not, the function returns.
	 * 
	 * If it is in the database, the user is prompted for the password. If this
	 * matches the value stored in the database, print Success and set loggedIn to
	 * true.
	 * 
	 * @param none
	 * @return none
	 */
	public static void staffLogIn() {
		System.out.println("\nPlease enter your email address:");
		String email = sc.nextLine();

		Account account = db.getStaffAccount(email);

		if (account == null) {
			System.out.println("\nEmail not found, please check the address and try again.");
		} else {
			System.out.println("\nPlease enter your password:");
			String password = sc.nextLine();

			if (password.equals(account.password)) {
				System.out.println("Success! You're logged in.");
				loggedIn = true;
				user = account;
			}
		}
	}

	/**
	 * <h2>Technician Log In</h2> Prompts the user for log in details.
	 * 
	 * First prompts for an email address, and checks if it is in the database. If
	 * it is not, the function returns.
	 * 
	 * If it is in the database, the user is prompted for the password. If this
	 * matches the value stored in the database, print Success and set loggedIn to
	 * true.
	 * 
	 * @param none
	 * @return none
	 */
	public static void technicianLogIn() {
		System.out.println("\nPlease enter your email address:");
		String email = sc.nextLine();

		Account account = db.getTechnicianAccount(email);

		if (account == null) {
			System.out.println("\nEmail not found, please check the address and try again.");
		} else {
			System.out.println("\nPlease enter your password:");
			String password = sc.nextLine();

			if (password.equals(account.password)) {
				System.out.println("Success! You're logged in.");
				loggedIn = true;
				user = account;
			}
		}
	}

	/**
	 * <h2>Create Ticket</h2> Prompts the user to enter details to create a new
	 * Ticket.
	 * 
	 * @param none
	 * @return none
	 */
	public static void createTicket() {

		// Initialise variables
		// Ticket vars
		String description;
		Ticket.Severity severity;
		Ticket.Status status;

		// ITSystem vars
		String os;
		String program;
		String version;

		// Object vars
		ITSystem itSystem;
		Ticket ticket;

		// Set status to open
		status = Ticket.Status.open;

		// Get user input for required Ticket variables
		System.out.println("\nEnter a description of the issue you're having:");
		description = sc.nextLine();
		
		// Choose severity of ticket
		System.out.println("\nSelect the severity:");
		System.out.println("0 - Low");
		System.out.println("1 - Medium");
		System.out.println("2 - High");

		severity = Ticket.Severity.values()[Integer.parseInt(sc.nextLine())];

		// Get user input for the required ITSystem variables
		System.out.println("\nEnter your operating system:");
		os = sc.nextLine();
		System.out.println("\nEnter the program you're using:");
		program = sc.nextLine();
		System.out.println("\nEnter the version of that program you're using:");
		version = sc.nextLine();

		// Create the ITSystem and Ticket and send to the db
		itSystem = new ITSystem(os, program, version);
		ticket = new Ticket(user, description, status, severity, itSystem);
		db.addTicket(ticket);
	}

	/**
	 * <h2>Update Ticket Severity</h2> Prompts the user to update a ticket's severity
	 * 
	 * @param none
	 * @return none
	 */
	public static void updateTicketSeverity() {
		// Print all tickets with numbers
		System.out.println("\nPlease select the ticket you wish to amend:");
		int i = 0;
		for (Ticket ticket : db.ticketList) {
			System.out.printf("%d - %s - %s\n", i, ticket.description, ticket.severity);
			i++;
		}

		// Accept input for which ticket
		int selection = Integer.parseInt(sc.nextLine());

		// Get ticket, and pop from db
		Ticket ticket = db.ticketList.get(selection);
		db.ticketList.remove(selection);

		// Choose severity of ticket
		System.out.println("\nPlease select the new severity of the ticket:");
		System.out.println("0 - Low");
		System.out.println("1 - Medium");
		System.out.println("2 - High");

		char severity = sc.nextLine().toCharArray()[0];

		switch (severity) {
		case '0':
			ticket.severity = Ticket.Severity.low;
			db.addTicket(ticket);
			System.out.println("Ticket severity updated!");
			break;
		case '1':
			ticket.severity = Ticket.Severity.medium;
			db.addTicket(ticket);
			System.out.println("Ticket severity updated!");
			break;
		case '2':
			ticket.severity = Ticket.Severity.high;
			db.addTicket(ticket);
			System.out.println("Ticket severity updated!");
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}

	/**
	 * <h2>Update Ticket Status</h2> Prompts the user to update a ticket's status
	 * 
	 * @param none
	 * @return none
	 */
	public static void updateTicketStatus(){
		// Print all tickets with numbers
		System.out.println("\nPlease select the ticket you wish to amend:");
		int i = 0;
		for (Ticket ticket : db.ticketList) {
			System.out.printf("%d - %s - %s\n", i, ticket.description, ticket.status);
			i++;
		}

		// Accept input for which ticket
		int selection = Integer.parseInt(sc.nextLine());

		System.out.println("\nPlease select the new status:");
		// Choose status of ticket
		System.out.println("0 - Open");
		System.out.println("1 - Closed");
		System.out.println("2 - Archived");

		char status = sc.nextLine().toCharArray()[0];

		switch (status) {
		case '0':
			db.ticketList.get(selection).updateStatus(Ticket.Status.open);
			System.out.println("Ticket status updated!");
			break;
		case '1':
			db.ticketList.get(selection).updateStatus(Ticket.Status.closed);
			System.out.println("Ticket status updated!");
			break;
		case '2':
			db.ticketList.get(selection).updateStatus(Ticket.Status.archived);
			System.out.println("Ticket status updated!");
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}

	/**
	 * <h2>Print All Tickets</h2> Prints a list of all tickets currently in the
	 * system.
	 * 
	 * @param none
	 * @return none
	 */
	public static void printAllTickets() {
		
		List<Ticket> ticketList = db.getTickets();

		// Print header
		System.out.printf("\n%-20s %-35s %-35s %-10s %-25s %-10s\n", "Created by", "Creation Date", "Action Date", "Status", "Assigned Technician", "Severity");

		// Loop through tickets to print
		for (Ticket ticket : ticketList) {

			// If the ticket is still open, print creationDate, status, tech and severity
			if (ticket.status == Ticket.Status.open) {
				System.out.printf("%-20s %-35s %-35s %-10s %-25s %-10s\n", ticket.creator.name, ticket.creationDate.toString(), "", ticket.status,
					ticket.assignedTechnician.name, ticket.severity);

			// If the ticket is closed, print creationDate, actionDate, status, tech and severity
			} else {
				System.out.printf("%-20s %-35s %-35s %-10s %-25s %-10s\n", ticket.creator.name, ticket.creationDate.toString(), ticket.actionDate.toString(), ticket.status,
					ticket.assignedTechnician.name, ticket.severity);
			}
		}

		System.out.printf("\n%-20s - %-20s", "Technician", "# of Tickets");
		for (Technician t : db.getAllTechnicians()) {
			System.out.printf("\n%-20s - %-20d", t.name, t.activeTickets.size());
		}
		System.out.println();
	}

	/**
	 * <h2>Print My Tickets</h2> Print all the tickets for the specified Technician.
	 * 
	 * @param technician
	 * @return none
	 */
	public static void printMyTickets(Technician technician) {

		List<Ticket> myTickets = db.getTickets(technician);

		if (myTickets.size() == 0) {

			System.out.println("You don't have any tickets at the moment!");
		} else {

			System.out.printf("\n%-35s %-35s %-10s %-25s %-10s\n", "Creation Date", "Creation Date", "Status", "Assigned Technician",
					"Severity");

			for (Ticket ticket : myTickets) {

				// If the ticket is still open, print creationDate, status, tech and severity
				if (ticket.status == Ticket.Status.open) {
					System.out.printf("%-20s %-35s %-35s %-10s %-25s %-10s\n", ticket.creator.name, ticket.creationDate.toString(), "", ticket.status,
						ticket.assignedTechnician.name, ticket.severity);

				// If the ticket is closed, print creationDate, actionDate, status, tech and severity
				} else {
				System.out.printf("%-20s %-35s %-35s %-10s %-25s %-10s\n", ticket.creator.name, ticket.creationDate.toString(), ticket.actionDate.toString(), ticket.status,
					ticket.assignedTechnician.name, ticket.severity);
				}
			}
		}
	}

	/**
	 * <h2>Print My Tickets</h2> Print all the tickets created by the specified Staff member.
	 * 
	 * @param Staff staff
	 * @return none
	 */
	public static void printMyTickets(Staff staff) {

		List<Ticket> myTickets = db.getTickets(staff);

		if (myTickets.size() == 0) {

			System.out.println("You don't have any tickets at the moment!");
		} else {

			System.out.printf("\n%-20s %-20s %-10s %-25s %-10s\n", "Creation Date", "Action Date", "Status", "Assigned Technician",
					"Severity");

			for (Ticket ticket : myTickets) {

				// If the ticket is still open, print creationDate, status, tech and severity
				if (ticket.status == Ticket.Status.open) {
					System.out.printf("%-20s %-20s %-10s %-25s %-10s\n", ticket.creationDate.toString(), "", ticket.status,
						ticket.assignedTechnician.name, ticket.severity);

				// If the ticket is closed, print creationDate, actionDate, status, tech and severity
				} else {
				System.out.printf("%-20s %-20s %-10s %-25s %-10s\n", ticket.creationDate.toString(), ticket.actionDate.toString(), ticket.status,
					ticket.assignedTechnician.name, ticket.severity);
				}
			}
		}
	}

	/**
	 * <h2>Print Report</h2> Prompts user for dates, then prints a report based on tickets in those dates
	 * The report displays:
	 *  - The number of Tickets submitted in the specified period, and how many of those are open, closed or archived.
	 *  - For all resolved Tickets, it displays who submitted it and when, who attended to it and how long it took to resolve it.
	 *  - For all open Tickets, it displays who submitted it and when, who is attending to it, and the severity of the ticket.
	 * 
	 * @param none
	 * @return none
	 */
	public static void printReport(){

		List<Ticket> ticketList;
		LocalDate start, end;

		// Get start/end date from user
		Boolean invalid = true;
		while (invalid) {
			System.out.println("Please enter start date, in yyyy-mm-dd format");
			String startRes = sc.nextLine();

			System.out.println("Please enter end date, in yyyy-mm-dd format");
			String endRes = sc.nextLine();

			try {
				start = LocalDate.parse(startRes);
				end = LocalDate.parse(endRes);
				ticketList = db.getTickets(start, end);
				invalid = false;

				// print report details
				System.out.println();
				System.out.printf("Report for period %s-%s", start.toString(), end.toString());
				System.out.println();

				// showing how many tickets were submitted in that period, and out of those, how many have been resolved and how many are outstanding.
				System.out.println("Ticket summary:");
				
				int countTicketsOpen = 0;
				int countTicketsClosed = 0;
				int countTicketsArchived = 0;
				for (Ticket ticket : ticketList) {
					if (ticket.status == Ticket.Status.open) { countTicketsOpen++; }
					if (ticket.status == Ticket.Status.closed) { countTicketsClosed++; }
					if (ticket.status == Ticket.Status.archived) { countTicketsArchived++; }
				}

				System.out.printf("Tickets open - %d\n", countTicketsOpen);
				System.out.printf("Tickets closed - %d\n", countTicketsClosed);
				System.out.printf("Tickets archived - %d\n", countTicketsArchived);
				System.out.println();

				//For all resolved tickets, the report must show who submitted it and when, who attended to it and how long it took to resolve it.
				System.out.println("Resolved tickets summary:");

				List<Ticket> resolvedTickets = ticketList.stream().filter(t -> t.status == Ticket.Status.closed || t.status == Ticket.Status.archived)
																  .collect(Collectors.toList());

				System.out.printf("%-20s %-20s %-20s %-10s %-25s %-10s\n", "Creator", "Creation Date", "Action Date", "Time Taken", "Assigned Technician", "Severity");
				for (Ticket ticket : resolvedTickets) {
					System.out.printf("%-20s %-20s %-20s %-10s %-25s %-10s\n", 
									ticket.creator.name, 
									ticket.creationDate.toString(), 
									ticket.actionDate.toString(), 
									Duration.between(ticket.creationDate.atTime(0, 0), ticket.actionDate.atTime(0, 0)).toDays(),
									ticket.assignedTechnician.name, 
									ticket.severity);
				}

				//For all outstanding tickets, the report must show who submitted it and when, and the severity of the ticket.
				System.out.println();
				System.out.println("Open tickets summary:");
				List<Ticket> openTickets = ticketList.stream().filter(t -> t.status == Ticket.Status.open)
																  .collect(Collectors.toList());

				System.out.printf("%-20s %-20s %-25s %-10s\n", "Creator", "Creation Date", "Assigned Technician", "Severity");
				for (Ticket ticket : openTickets) {
					System.out.printf("%-20s %-20s %-25s %-10s\n", 
									ticket.creator.name, 
									ticket.creationDate.toString(), 
									ticket.assignedTechnician.name, 
									ticket.severity);
				}
			} catch (Exception e) {
				System.out.println("Invalid date format.");
			}
		}
	}
}