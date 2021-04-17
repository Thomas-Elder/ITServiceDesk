public class Technician extends Account {

    public int level;

    public List<Ticket> activeTickets = new ArrayList<Ticket>();

    Technician(String email, String name, String number, String password, int level) {
        super(email, name, number, password);
        this.level = level;

        this.technician = true;
    }

    public List<Ticket> getTickets() {
        return activeTickets;
    }

    public void addTicket(Ticket ticket) {
        activeTickets.add(ticket);
    }

    public void closeTicket(Ticket ticket) {
        activeTicket.remove(ticket);
    }
}
