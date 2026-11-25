package Backend.model;

public class HistoryData {
    String category;
    String title;
    String amount;
    String description;
    String date;
    String createdDate;

    public HistoryData(String amount, String category, String createdDate, String date, String description, String title) {
        this.amount = amount;
        this.category = category;
        this.createdDate = createdDate;
        this.date = date;
        this.description = description;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    

}