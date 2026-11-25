package Backend.model;

public class ExpenseData {
    int user_id;
    String title;
    String amount;
    String category;
    String description;
    String date;
    public ExpenseData(String amount, String category, String description, String title, int user_id , String date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.title = title;
        this.user_id = user_id;
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
