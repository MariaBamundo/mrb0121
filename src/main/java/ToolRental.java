import model.RentalAgreement;
import model.Tool;

import java.text.ParseException;
import java.util.Scanner;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolRental
{
    // Global Variables
    Scanner scanner;
    HashMap<String, Object> tools;


    public ToolRental() {
        scanner = new Scanner(System.in);
        tools = new HashMap<String, Object>();

        populateTools();
    }

    void populateTools() {
        tools.put("LADW", new Tool("Ladder", "Werner", "LADW"));
        tools.put("CHNS", new Tool("Chainsaw", "Stihl", "CHNS"));
        tools.put("JAKR", new Tool("Jackhammer", "Ridgid", "JAKR"));
        tools.put("JAKD", new Tool("Jackhammer", "DeWalt", "JAKD"));
    }

    String getUserInputToolCode() {
        String input = "";
        boolean inputValid = false;

        while(!inputValid) {
            System.out.print("Enter the tool code: ");
            input = scanner.next();

            if(tools.containsKey(input)) {
                inputValid = true;
            } else {
                System.out.println("Invalid tool code"); // wanted this to be system.err but it printed out of order
            }
        }

        return input;
    }

    String getUserInput(String prompt, String validationRegex, String errorMessage) {
        String input = "";
        boolean inputValid = false;

        while(!inputValid) {
            System.out.print(prompt);
            input = scanner.next();

            if(input.matches(validationRegex)) {
                inputValid = true;
            } else {
                System.out.println(errorMessage); // wanted this to be system.err but it printed out of order
            }
        }

        return input;
    }

    void checkout() throws ParseException {
        String toolCode = getUserInputToolCode();
        Tool tool = (Tool)tools.get(toolCode);  // temp until DB

        String rentalDayCountString = getUserInput("Enter the rental day count : ","^[1-9]\\d*$", "Rental day count must be a whole number greater than 0");
        int rentalDayCount = Integer.parseInt(rentalDayCountString);

        String discountPercentString = getUserInput("Enter the discount percent: ","^(0|[1-9][0-9]?|100)$", "Discount percent must be a whole number between 0 and 100");
        int discountPercent = Integer.parseInt(discountPercentString);

        String checkoutDateString = getUserInput("Enter the checkout date in the following format - mm/dd/yy): ","(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/]\\d\\d", "Invalid date format");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date checkoutDate = formatter.parse(checkoutDateString);

        generateRentalAgreement(tool, rentalDayCount, discountPercent, checkoutDate);
    }

    Date calculateDueDate() {
        return new Date();
    }

    int calculateChargeDays() {
        return 1;
    }

    Double calculateDiscountAmount() {
        return 1.1;
    }

    void generateRentalAgreement(Tool tool, int rentalDayCount, int discountPercent, Date checkoutDate) {
        /*
        model.Tool code - Specified at checkout
        model.Tool type - From tool info
        model.Tool brand - From tool info
        Rental days - Specified at checkout
        Check out date  - Specified at checkout
        Due date - Calculated from checkout date and rental days.
        Daily rental charge - Amount per day, specified by the tool type.
        Charge days - Count of chargeable days, from day after checkout through and including due date, excluding “no charge” days as specified by the tool type.
                Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up to cents.
                Discount percent - Specified at checkout.
        Discount amount - calculated from discount % and pre-discount charge.  Resulting amount rounded half up to cents.
                Final charge - Calculated as pre-discount charge - discount amount.
         */
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDayCount, checkoutDate, discountPercent);

        // move these to RentalAgreement model?
        System.out.println("model.Tool code: " + tool.getToolCode());
        System.out.println("model.Tool type: " + tool.getToolType());
        System.out.println("model.Tool brand: " + tool.getToolBrand());
        System.out.println("Rental days: " + rentalAgreement.getRentalDays());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Check out date: " + formatter.format(rentalAgreement.getCheckoutDate())); // need to format as MM/dd/yy
        System.out.println("Due date: " + formatter.format(rentalAgreement.getDueDate())); // need to format as MM/dd/yy

        System.out.println("Daily rental charge: $" + rentalAgreement.getDailyRentalCharge()); // need to format as money
        System.out.println("Charge days: " + rentalAgreement.getChargeDays());
        System.out.println("Pre-discount charge: " + rentalAgreement.getPreDiscountCharge());
        System.out.println("Discount percent: %" + rentalAgreement.getDiscountPercent());
        System.out.println("Discount amount: $" + rentalAgreement.getDiscountAmount()); // need to format as money
        System.out.println("Final charge: $" + rentalAgreement.getFinalCharge()); // need to format as money
    }
}
