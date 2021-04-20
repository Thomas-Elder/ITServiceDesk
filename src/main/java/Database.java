
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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

        Random random = new Random();

        if (newTicket.severity == Ticket.Severity.high) {

            // Get a list of level 2 technicians
            List<Technician> level2Technicians = technicianAccountsHashMap.values().stream().filter(t -> t.level == 2)
                    .collect(Collectors.toList());

            int min = 100;

            // Get the minimum number of tickets anyone has
            for (int i = 0; i < level2Technicians.size(); i++) {
                if (level2Technicians.get(i).activeTickets.size() < min) {
                    min = level2Technicians.get(i).activeTickets.size();
                }
            }

            // Get a list of all techs with the min # of tickets, and randomly assign
            List<Technician> minTicketTechList = new ArrayList<Technician>();
            for (Technician t : level2Technicians) {
                if (t.activeTickets.size() == min) { minTicketTechList.add(t); }
            }

            // Get a random index...
            int randomIndex = random.nextInt(minTicketTechList.size());

            // Assign the ticket to the tech at that index
            newTicket.assignedTechnician = minTicketTechList.get(randomIndex);
            minTicketTechList.get(randomIndex).addTicket(newTicket);

        } else {
            // Get a list of level 2 technicians
            List<Technician> level1Technicians = technicianAccountsHashMap.values().stream().filter(t -> t.level == 1)
                    .collect(Collectors.toList());

            int min = 100;

            // Get the minimum number of tickets anyone has
            for (int i = 0; i < level1Technicians.size(); i++) {
                if (level1Technicians.get(i).activeTickets.size() < min) {
                    min = level1Technicians.get(i).activeTickets.size();
                }
            }

            // Get a list of all techs with the min # of tickets, and randomly assign
            List<Technician> minTicketTechList = new ArrayList<Technician>();
            for (Technician t : level1Technicians) {
                if (t.activeTickets.size() == min) { minTicketTechList.add(t); }
            }

            // Get a random index...
            int randomIndex = random.nextInt(minTicketTechList.size());

            // Assign the ticket to the tech at that index
            newTicket.assignedTechnician = minTicketTechList.get(randomIndex);
            minTicketTechList.get(randomIndex).addTicket(newTicket);
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
