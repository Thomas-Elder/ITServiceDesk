import java.util.Date;

/**
 * <h1>Ticket</h1>
 *
 * @param description  The description of the ticket
 * @param creationDate The date the ticket was cretaed
 * @param status       The {@link Status} of the ticket, Accepted options:
 *                     <code>open</code>, <code>closed</code>,
 *                     <code>archived</code>
 * @param severity     The {@link Severity} of the ticket, Accepted options:
 *                     <code>critical</code>, <code>high</code>,
 *                     <code>medium</code>, <code>low</code>
 */
public class Ticket {
	public enum Status {
		open, closed, archived;
	}

	public enum Severity {
		critical, high, medium, low;
	}

	Ticket(String description, Date creationDate, Status status, Technician assignedTechnician, Severity severity) {

	}
}
