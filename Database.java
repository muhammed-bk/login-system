package loginsystem;
import loginsystem.Database;
import loginsystem.RegisterPage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Database {
    private static final String DB_URL = "jdbc:sqlite=" + "muhammed_users.db";
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir")+ "/muhammed_users.db");
        } catch (ClassNotFoundException  |SQLException e) {
            System.out.println("Error connecting to the database -> " + e.getMessage());
        }
            return conn;
    }
    public static void createUserTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS muhammed_users(
                 id INTEGER PRIMARY KEY AUTOINCREMENT,
                 username TEXT NOT NULL UNIQUE, 
                 password TEXT NOT NULL  
                 );
 """;
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("users table ready.");
        } catch (SQLException e) {
            System.out.println("Error creating user table" + e.getMessage());
        }
    }
    public static HashMap<String,String> loadLoginInfo(){
        HashMap<String,String> loginInfo = new HashMap<>();

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT username , password FROM muhammed_users ")){
            while(rs.next()){
                loginInfo.put(rs.getString("username"),rs.getString("password"));
            }
        }catch (SQLException ex){
            System.out.println("Error loading login info" + ex.getMessage());
        }
        return loginInfo;
    }


}
