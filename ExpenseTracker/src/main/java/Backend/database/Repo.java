package Backend.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Repo {
	 private static final String url = "jdbc:mysql://127.0.0.1:3307/expense_tracker";
	    private static final String username = "root";
	    private static final String password = "vengatesh";

    private static Connection con;

    private Repo(){
        
    }

    public static Connection getInstance() {
        try {
            if (con == null || con.isClosed()) {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
            
                return con;
            }
        } catch (Exception e) {
            System.out.println("error in connection");
            System.out.println(e.getMessage());
        }
        return con;
    }
}

