import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Capstone1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Home screen");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment(Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option :");

            // converts scanner input to uppercase
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    transaction(scanner, true);
                    break;
                case "P":
                    transaction(scanner, false);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    System.out.println("Exiting now, Good bye!");
                    return;


            }
        }


    }

    // Prompts user questions asking for inputs
    private static void transaction(Scanner scanner, boolean isDeposit) {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        //check up on this logic

        // input from user must be a double since it is money

        //Coverts double into string
        String amountString = String.valueOf(amount);


        String finalAmount = null;
        String type = null;
        // Appending - to all transactions with payments
        if (!isDeposit) {
            finalAmount = "-" + amountString;
            type = "PAYMENT";

        } else {
            finalAmount = amountString;
            type = "DEPOSIT";
        }

        LocalDate date = LocalDate.now();
        String dateString = date.toString();
        LocalTime time = LocalTime.now();
        String timeString = time.toString();

        // after saving values into variables it will be used in this method to save the inputs to a csv file
        savedScanner(dateString, timeString, description, vendor, finalAmount, type);
        scanner.nextLine();


    }

    // saves users inputs into a predefined format into a csv file
    public static void savedScanner(String date, String time, String description, String vendor, String amount, String type) {

//        String vendor, double
        try (FileWriter writer = new FileWriter("src/main/resources/transactions.csv", true)) {
            //Transaction class formats the input of the user to specific format
            Transactions transactions = new Transactions(date, time, description, vendor, amount, type);
            writer.write(transactions + "\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void ledgerMenu(Scanner scanner) {
        boolean viewing = true;

        while (viewing) {
            System.out.println("\n=== Ledger Menu ===");
            System.out.println("A) All - displays all entries");
            System.out.println("D) View Deposits Only");
            System.out.println("P) View Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    //change method name
                    depositTransactions("DEPOSIT");
                    break;
                case "P":
                    depositTransactions("PAYMENT");
                    break;
                case "R":
                case "H":
                    viewing = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    //Scanner reads from the file adding each line to a list of strings and then iterates through the list before printing it reverses the list and prints each line
    public static void displayAllTransactions() {
        try {
            File file = new File("src/main/resources/transactions.csv");

            List<String> fileText = new ArrayList<>();
            Scanner reader = null;

            reader = new Scanner(file);

            while (reader.hasNextLine()) {
                fileText.add(reader.nextLine());
            }

            reader.close();

            Collections.reverse(fileText);

            System.out.println("=== Transactions ===");

            for (String line : fileText) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("error...");
        }
    }

    //This determines if it is a deposit or a payment
    public static void depositTransactions(String type) {
        try {
            File file = new File("src/main/resources/transactions.csv");
            List<String> fileText = new ArrayList<>();
            Scanner reader = new Scanner(file);

            String avoid;
            if (type.contains("PAYMENT")) {
                avoid = "DEPOSIT";
            } else {
                avoid = "PAYMENT";
            }
            // reads until the last line and checks each line for the value of the line to see the types of valu
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains(avoid)) {
                    continue; // skip this line
                }
                fileText.add(line); // add remaining lines
            }

            reader.close();

            Collections.reverse(fileText); // newest first

            // print based on payment or deposit
            System.out.println("=== " + type + "Transactions ===");
            for (String line : fileText) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found!");
        }
    }
}
