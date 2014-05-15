package ajou.cse.oop.models;

public class Schedule {

    private String title;
    private Date startDate;
    private Date endDate;

    public Schedule(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object schedule) {
        Schedule target = (Schedule) schedule;

        return this.getTitle().equals(target.getTitle())
                && this.getStartDate().equals(target.getStartDate())
                && this.getEndDate().equals(target.getEndDate());
    }

    @Override
    public String toString() {
        return String.format("<%s>\n시작시간: %s\n종료시간: %s", title, startDate, endDate);
    }
}
