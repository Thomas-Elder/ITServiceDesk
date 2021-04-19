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
		high, medium, low;
	}

	public String description;
	public Date creationDate;
	public Date actionDate;
	public Status status;
	public Technician assignedTechnician;
	public Severity severity;
	public ITSystem itsystem;

	Ticket(String description, Status status, Severity severity, ITSystem itsystem) {
		this.description = description;
		this.status = status;
		this.severity = severity;
		this.itsystem = itsystem;

		this.creationDate = new Date();
	}

	public void updateStatus(Status status) {

		// If we're closing the ticket, update the actionDate
		if (status == Ticket.Status.closed) {
			this.actionDate = new Date();
		}

		// If we're going from open to archived, update the actionDate
		if (status == Ticket.Status.archived && this.status == Ticket.Status.open) {
			this.actionDate = new Date();
		}

		this.status = status;
	}
}
