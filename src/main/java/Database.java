import java.util.HashMap;

public class Database {
    
    HashMap<String, Account> accountHashMap = new HashMap<String, Account>(); 

    Database(){

    }

    public void addAccount(Account newAccount){
        accountHashMap.put(newAccount.email, newAccount);
    }

    public Account getAccount(String email){
        return accountHashMap.get(email);
    }
}
