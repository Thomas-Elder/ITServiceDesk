import java.time.LocalDate;

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
		high, medium, low;
	}

	public Account creator;
	public String description;
	public LocalDate creationDate;
	public LocalDate actionDate;
	public Status status;
	public Technician assignedTechnician;
	public Severity severity;
	public ITSystem itsystem;

	Ticket(Account creator, String description, Status status, Severity severity, ITSystem itsystem) {
		this.creator = creator;
		this.description = description;
		this.status = status;
		this.severity = severity;
		this.itsystem = itsystem;

		this.creationDate = LocalDate.now();
	}

	public void updateStatus(Status status) {

		// If we're closing the ticket, update the actionDate
		if (status == Ticket.Status.closed) {
			this.actionDate = LocalDate.now();
		}

		// If we're going from open to archived, update the actionDate
		if (status == Ticket.Status.archived && this.status == Ticket.Status.open) {
			this.actionDate = LocalDate.now();
		}

		this.status = status;
	}
}
