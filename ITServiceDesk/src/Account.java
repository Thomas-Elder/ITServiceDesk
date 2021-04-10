public class Account {
    
    public String email;
    public String name;
    public String number;
    public String password;
    public Boolean technician;

    Account(String email, String name, String number, String password) {
        this.email = email;
        this.name = name;
        this.number = number; 
        this.password = password;

        this.technician = false;
    }
}
