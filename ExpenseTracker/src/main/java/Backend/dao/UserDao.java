package Backend.dao;

import Backend.database.Repo;
import Backend.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao {


    public User signUp(User user) {
        int userId = 0;
        boolean found = false;
        String name = null;
        String query = "INSERT INTO users(name, email, password, phone_number) VALUES(?, ?, ?, ?)";
        
        try {
            Connection con = Repo.getInstance();
            PreparedStatement state = con.prepareStatement(query, RETURN_GENERATED_KEYS);
            state.setString(1, user.getName());
            state.setString(2, user.getEmail());
            state.setString(3, user.getPassword());
            state.setString(4, user.getPhoneNumber());
            int result = state.executeUpdate();
            found = true;
            name = user.getName();
            if (result > 0) {
                ResultSet generatedKeys = state.getGeneratedKeys();
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                }
                generatedKeys.close();
                User user1 = new User(found, name, userId);
                state.close();
                return user1;
            }
            state.close();
        
            

        } catch (Exception e) {
            System.out.println("error in signup");
            e.printStackTrace();
        }
        return null;
    }


//     this method for signin
    public User signIn(String email, String password) {

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Connection con = Repo.getInstance();

            PreparedStatement state = con.prepareStatement(query);

            state.setString(1, email);
            state.setString(2, password);

            ResultSet res = state.executeQuery();
            if (res.next()) {
                int userId = res.getInt("user_id");
                String name = res.getString("name");
                state.close();
                res.close();
                return new User(true, name, userId);
            }
            state.close();
            res.close();
            
            
        } catch (Exception e) {
            System.out.println("error in signin");
            e.printStackTrace();
        }

        return null;
    }
}
