package Backend.dao;

import Backend.database.Repo;
import Backend.model.ExpenseData;
import Backend.model.HistoryData;
import Backend.model.profile;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExpenseDao {

	// add expense method
	public boolean AddExpense(ExpenseData data) {

		try {
			Connection con = Repo.getInstance();

			String categoryQuery = "SELECT category_id FROM categories WHERE category_name = ?";

			PreparedStatement state = con.prepareStatement(categoryQuery);

			state.setString(1, data.getCategory());

			ResultSet res = state.executeQuery();

			if (res.next()) {

				int category_id = res.getInt("category_id");

				String query = "INSERT INTO expenses "
						+ "(user_id, category_id, amount, description, expense_date, title) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement insert = con.prepareStatement(query);

				insert.setInt(1, data.getUser_id());
				insert.setInt(2, category_id);
				insert.setBigDecimal(3, new BigDecimal(data.getAmount()));
				insert.setString(4, data.getDescription());
				insert.setDate(5, Date.valueOf(data.getDate()));
				insert.setString(6, data.getTitle());

				int result = insert.executeUpdate();

				insert.close();
				res.close();
				state.close();

				return result > 0;
			}

			res.close();
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	// view history method
	public ArrayList<HistoryData> getHistory(int userid) {
		String query = "SELECT c.category_name AS category, e.title, e.amount, "
				+ "e.description, e.expense_date AS date, e.created_at AS createdDate "
				+ "FROM expenses e JOIN categories c ON e.category_id = c.category_id "
				+ "WHERE e.user_id = ?";

		ArrayList<HistoryData> history = new ArrayList<>();

		try {
			Connection con = Repo.getInstance();
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, userid);
			ResultSet res = state.executeQuery();

			while (res.next()) {
				HistoryData data = new HistoryData(
					    res.getBigDecimal("amount").toString(),
					    res.getString("category"),
					    res.getTimestamp("createdDate").toLocalDateTime().toString(),
					    res.getDate("date").toLocalDate().toString(),
					    res.getString("description"),
					    res.getString("title")
					);
				history.add(data);
			}

			res.close();
			state.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return history;
	}
	//

	public profile getProfile(int userId){
		String query = """
				SELECT u.created_at,
				       u.email,
				       u.name,
				       COUNT(e.expense_id) AS numberOfExpense,
				       SUM(e.amount) AS totalAmount,
				       u.phone_number
				FROM users u
				JOIN expenses e ON u.user_id = e.user_id
				WHERE u.user_id = ?
				GROUP BY u.user_id, u.created_at, u.email, u.name, u.phone_number
				""";

		try {
			Connection con = Repo.getInstance();
			PreparedStatement state = con.prepareStatement(query);
			state.setInt(1, userId);
			ResultSet res = state.executeQuery();
/// public profile(String createdDate, String email, String name, int numberOfExpense, 
 //  String phoneNumber , double amount) {

			if (res.next()) {
				profile data = new profile(
						res.getTimestamp("created_at").toLocalDateTime().toString(),
						res.getString("email"),
						res.getString("name"),
						res.getInt("numberOfExpense"),
						res.getString("phone_number"),
						res.getBigDecimal("totalAmount").doubleValue()
				);
				res.close();
				state.close();
				return data;
			}

			res.close();
			state.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
}
