public class Technician extends Account {

    public int level;

    Technician(String email, String name, String number, String password, int level) {
        super(email, name, number, password);
        this.level = level;

        this.technician = true;
    }
}
