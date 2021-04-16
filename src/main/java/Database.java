import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    
    HashMap<String, Staff> staffAccountsHashMap = new HashMap<String, Staff>();
    HashMap<String, Technician> technicianAccountsHashMap = new HashMap<String, Technician>();
    List<Ticket> ticketList = new ArrayList<Ticket>(); 

    Database(){

    }

    public void addStaffAccount(Staff newStaffAccount){
        staffAccountsHashMap.put(newStaffAccount.email, newStaffAccount);
    }

    public Staff getStaffAccount(String email){
        return staffAccountsHashMap.get(email);
    }

    public void addTechnicianAccount(Technician newTechnicianAccount){
        technicianAccountsHashMap.put(newTechnicianAccount.email, newTechnicianAccount);
    }

    public Technician getTechnicianAccount(String email){
        return technicianAccountsHashMap.get(email);
    }

    public List<Technician> getAllTechnicians() {
        return new ArrayList<Technician>(technicianAccountsHashMap.values());
    } 

    public void addTicket(Ticket newTicket){
        ticketList.add(newTicket);
    }

    public void updateTickets(List<Ticket> ticketlist) {
        this.ticketList = ticketlist;
    }

    public List<Ticket> getTickets() {
        return ticketList;
    }

    public List<Ticket> getTickets(Technician technician) {
        return this.ticketList.stream()
        .filter(t -> t.assignedTechnician.equals(technician))
        .collect(Collectors.toList());
    }
}
