import model.Tool;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.OffsetDateTime;

public class Sandbox {

    public static void main(String[] args) {


        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime future = now.plusDays(100);


    }

    private int calculateBillableDays(OffsetDateTime startDate, OffsetDateTime endDate, Tool tool) {

        int billableDays = 0;

        OffsetDateTime currentDate = startDate;
        while (currentDate.isBefore(endDate)) {
            currentDate = currentDate.plusDays(1);

            if(!isHoliday(currentDate) && !isWeekend(currentDate)) {
                billableDays++;
                continue;
            }

            if(isHoliday(currentDate) && tool.getHolidayCharge()) {
                    billableDays++;
                    continue;
            }

            if(isWeekend(currentDate) && tool.getWeekendCharge()) {
                billableDays++;
            }
        }
        return billableDays;
    }

    private boolean isWeekend(OffsetDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(OffsetDateTime date) {
        return isIndependenceDayObserved(date) || isLaborDay(date);
    }

    private boolean isIndependenceDayObserved(OffsetDateTime date) {
        if (date.getMonth() != Month.JULY) {
            return false;
        }
        if (date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return true;
        }
        if (date.getDayOfMonth() == 4 && !isWeekend(date)) {
            return true;
        }
        if (date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    private boolean isLaborDay(OffsetDateTime date) {
        if(date.getMonth() != Month.SEPTEMBER) {
            return false;
        }
        if(date.getDayOfMonth() < 8 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
            return true;
        }
        return false;
    }
}
