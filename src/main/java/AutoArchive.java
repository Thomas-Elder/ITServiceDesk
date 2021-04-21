public class AutoArchive implements Runnable {

    private int selection;

    public AutoArchive(int selection) {
        this.selection = selection;
    }

    public void run() {
        try {
            System.out.println("This has executed");
            db.ticketList.get(selection).updateStatus(Ticket.status.archived);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}