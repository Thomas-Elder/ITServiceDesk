/**
 * AutoArchive is a helper Class that is triggered after 24 hours of a ticket being marked resolved
 */
public class AutoArchive implements Runnable {

    private int selection;
    private Database db;

    public AutoArchive(int selection, Database db) {
        this.selection = selection;
        this.db = db;
    }

    @Override
    public void run() {
        try {
            db.ticketList.get(selection).updateStatus(Ticket.Status.archived);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}