package model;
import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RentalAgreement {
//    String toolCode; //Specified at checkout
//    String toolType; // ENUM? From tool info
//    String toolBrand; // From tool info
    Tool tool;
    int rentalDays; // Specified at checkout
    Date checkoutDate; //Specified at checkout
    Date dueDate; // Calculated from checkout date and rental days.
    double dailyRentalCharge; //Amount per day, specified by the tool type.
    int chargeDays; // Count of chargeable days, from day after checkout through and including due date, excluding “no charge” days as specified by the tool type.
    double preDiscountCharge; // Calculated as charge days X daily charge. Resulting total rounded half up to cents.
    int discountPercent; // Specified at checkout.
    double discountAmount; // calculated from discount % and pre-discount charge.  Resulting amount rounded half up to cents.
    double finalCharge; // Calculated as pre-discount charge - discount amount.

    public RentalAgreement(Tool tool, int rentalDays, Date checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = calculateDueDate();
        this.dailyRentalCharge = this.tool.getDailyCharge();
        this.chargeDays = 0; // temp calculateChargeDays();
        this.preDiscountCharge = calculatePreDiscountCharge();
        this.discountPercent = discountPercent;
        this.discountAmount = calculateDiscountAmount();
        this.finalCharge = calculateFinalCharge();
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }

    Date calculateDueDate(){
        Instant instant = this.checkoutDate.toInstant();
        return Date.from(instant.plus(this.rentalDays, ChronoUnit.DAYS));
    }

    int calculateChargeDays() {
        // if holiday is between checkoutDate to dueDate
        return 0; // temp
    }

    double calculatePreDiscountCharge() {
        return Math.round((this.chargeDays*this.dailyRentalCharge)*100.0)/100.0;
    }

    double calculateDiscountAmount() {
        return Math.round(((this.discountPercent*.01)*this.preDiscountCharge)*100.0)/100.0;
    }

    double calculateFinalCharge() {
        return Math.round((this.preDiscountCharge-this.discountAmount)*100.0)/100.0;
    }
}
