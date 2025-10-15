public class Transactions {
    private String date;
    private String description;
    private String vendor;
    private double amount;

    public Transactions(String date, String description, String vendor, double amount) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-15s %-15s %10.2f", date, description, vendor, amount);
    }
}
