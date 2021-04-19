
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    HashMap<String, Staff> staffAccountsHashMap = new HashMap<String, Staff>();
    HashMap<String, Technician> technicianAccountsHashMap = new HashMap<String, Technician>();
    List<Ticket> ticketList = new ArrayList<Ticket>();

    Database() {

    }

    public void addStaffAccount(Staff newStaffAccount) {
        staffAccountsHashMap.put(newStaffAccount.email, newStaffAccount);
    }

    public Staff getStaffAccount(String email) {
        return staffAccountsHashMap.get(email);
    }

    public void addTechnicianAccount(Technician newTechnicianAccount) {
        technicianAccountsHashMap.put(newTechnicianAccount.email, newTechnicianAccount);
    }

    public Technician getTechnicianAccount(String email) {
        return technicianAccountsHashMap.get(email);
    }

    public List<Technician> getAllTechnicians() {
        return new ArrayList<Technician>(technicianAccountsHashMap.values());
    }

    public void addTicket(Ticket newTicket) {

        if (newTicket.severity == Ticket.Severity.high) {

            List<Technician> getl2techs = technicianAccountsHashMap.values().stream().filter(t -> t.level == 2)
                    .collect(Collectors.toList());

            int min = 100;
            int j = 0;

            for (int i = 0; i < getl2techs.size(); i++) {
                if (getl2techs.get(i).activeTickets.size() < min) {
                    min = getl2techs.get(i).activeTickets.size();
                    j = i;
                }
            }

            newTicket.assignedTechnician = getl2techs.get(j);
            getl2techs.get(j).addTicket(newTicket);

        } else {
            List<Technician> getl1techs = technicianAccountsHashMap.values().stream().filter(t -> t.level == 1)
                    .collect(Collectors.toList());

            int min = 100;
            int k = 0;

            for (int i = 0; i < getl1techs.size(); i++) {
                if (getl1techs.get(i).activeTickets.size() < min) {
                    min = getl1techs.get(i).activeTickets.size();
                    k = i;
                }
            }

            newTicket.assignedTechnician = getl1techs.get(k);
            getl1techs.get(k).addTicket(newTicket);
        }

        ticketList.add(newTicket);
    }

    public void updateTickets(List<Ticket> ticketlist) {
        this.ticketList = ticketlist;
    }

    public List<Ticket> getTickets() {
        return ticketList;
    }

    public List<Ticket> getTickets(LocalDate start, LocalDate end) {

        return this.ticketList.stream().filter(t -> t.creationDate.isAfter(start))
                                       .filter(t -> t.creationDate.isBefore(end))
                                       .collect(Collectors.toList());
    }

    public List<Ticket> getTickets(Technician technician) {
        return this.ticketList.stream().filter(t -> t.assignedTechnician.email.equals(technician.email))
                .collect(Collectors.toList());
    }

    public List<Ticket> getTickets(Staff staff) {
        return this.ticketList.stream().filter(t -> t.creator.email.equals(staff.email))
                .collect(Collectors.toList());
    }
}
