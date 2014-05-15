package ajou.cse.oop.models;

public class Date {

    public static final int INVALID_COMPARE_DATE = 0;
    public static final int AFTER_THAN_COMPARE_DATE = 1;
    public static final int BEFORE_THAN_COMPARE_DATE = -1;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public static long getTimestamp(Date date) {
        int standardYear = 1900;
        int standardMonth = 1;
        int standardDay = 1;
        int standardHour = 0;
        int standardMinute = 0;

        long gapYear =  (date.getYear() - standardYear) * 365 * 24 * 60 * 60;
        long gapMonth =  (date.getMonth() - standardMonth) * 30 * 24 * 60 * 60;
        long gapDay = (date.getDay() - standardDay) * 24 * 60 * 60;
        long gapHour = (date.getHour() - standardHour) * 60 * 60;
        long gapMinute = (date.getMinute() - standardMinute) * 60;

        return gapYear + gapMonth + gapDay + gapHour + gapMinute;
    }

    public int compareTo(Date date) {
        if (date == null) {
            return INVALID_COMPARE_DATE;
        }

        long thisTimestamp = getTimestamp(this);
        long compareTimestamp = getTimestamp(date);

        return (thisTimestamp - compareTimestamp > 0) ? AFTER_THAN_COMPARE_DATE : BEFORE_THAN_COMPARE_DATE;
    }

    public boolean isSameDayWith(Date date) {
        return year == date.getYear()
                && month == date.getMonth()
                && day == date.getDay();
    }

    @Override
    public boolean equals(Object date) {
        Date target = (Date) date;
        return getTimestamp(this) == getTimestamp(target);
    }

    @Override
    public String toString() {
        return String.format("%d년 %d월 %d일 %d시 %d분", year, month, day, hour, minute);
    }
}
