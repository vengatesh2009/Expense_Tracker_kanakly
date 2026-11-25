package Backend.model;

public class profile{
    String name;
    String email;
    String createdDate;
    String phoneNumber;
    int numberOfExpense;
    double amount;

    public profile(String createdDate, String email, String name, int numberOfExpense, 
        String phoneNumber , double amount) {
        this.createdDate = createdDate;
        this.email = email;
        this.name = name;
        this.numberOfExpense = numberOfExpense;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfExpense() {
        return numberOfExpense;
    }

    public void setNumberOfExpense(int numberOfExpense) {
        this.numberOfExpense = numberOfExpense;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}