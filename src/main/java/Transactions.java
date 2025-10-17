public class Transactions {
    private String date;
    private String description;
    private String vendor;
    private String amount;
    private String time;
    private String type;

    public Transactions(String date, String description, String vendor, String amount,String time, String type) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.time = time;
        this.type = type;
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

    public String getAmount() {
        return amount;
    }

    public String time(){
        return time;
    }

    @Override
    public String toString() {
        return String.format(" %s |%s | %s | %s | %s | %s",type, date, description, vendor, amount, time);
    }
}
