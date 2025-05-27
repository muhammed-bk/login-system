package loginsystem;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        Database.createUserTable();
        HashMap<String, String> loginInfo =Database.loadLoginInfo();
        new RegisterPage();
    }
}
