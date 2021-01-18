package model;

public class Tool {
    String toolType; // ENUM?
    String toolBrand;
    String toolCode;
    Double dailyCharge;
    Boolean weekdayCharge;
    Boolean weekendCharge;
    Boolean holidayCharge;

    public Tool(String toolType, String toolBrand, String toolCode) {
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.toolCode = toolCode;
        initializeCharges(this.toolType);
    }

    private void initializeCharges(String toolType) {
        if (toolType == "Ladder") {
            this.dailyCharge = 1.99;
            this.weekdayCharge = true;
            this.weekendCharge = true;
            this.holidayCharge = false;
        } else if (toolType == "Chainsaw") {
            this.dailyCharge = 1.49;
            this.weekdayCharge = true;
            this.weekendCharge = false;
            this.holidayCharge = true;
        } else {
            this.dailyCharge = 2.99;
            this.weekdayCharge = true;
            this.weekendCharge = false;
            this.holidayCharge = false;
        }
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(Boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public Boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(Boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public Boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(Boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}
