package loginsystem;
import loginsystem.Database;
import loginsystem.RegisterPage;
import java.util.HashMap;


public class usernameandPasswords {
    private HashMap<String, String> logininfo = new HashMap<>();


    public usernameandPasswords() {
        logininfo.put("username", "password");
        logininfo.put("username2", "password2");
        logininfo.put("username3", "password3");
        logininfo.put("username4", "password4");
    }


    public HashMap<String, String> getLogininfo() {
        return new HashMap<>(logininfo);
    }
}
