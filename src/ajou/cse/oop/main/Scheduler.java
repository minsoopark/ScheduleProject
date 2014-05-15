package ajou.cse.oop.main;

import ajou.cse.oop.models.Date;
import ajou.cse.oop.models.Exceptions;
import ajou.cse.oop.models.Schedule;
import ajou.cse.oop.utils.ScheduleUtil;

import java.util.Scanner;

public class Scheduler {
    public static void main(String args[]) {
        Schedule[] schedules = new Schedule[]{};

        while (true) {
            System.out.println("메뉴를 선택하세요!");
            System.out.println("1. 일정 추가하기");
            System.out.println("2. 일정 삭제하기");
            System.out.println("3. 전체 일정 보기");
            System.out.println("4. 종료");
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            int menu = scanner.nextInt();
            System.out.println();

            if (menu == 4) {
                return;
            }

            switch (menu) {
                case 1:
                    System.out.println("일정을 입력하세요!");

                    System.out.print("제목: ");
                    String title = scanner.next();

                    System.out.print("시작 시간: ");
                    Date startDate = inputDate();

                    System.out.print("종료 시간: ");
                    Date endDate = inputDate();

                    Schedule schedule = new Schedule(title, startDate, endDate);

                    try {
                        System.out.println();
                        schedules = ScheduleUtil.addSchedule(schedules, schedule);
                    } catch (Exceptions.SameScheduleExistsException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                    } catch (Exceptions.IllegalDateFormatException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                    }

                    break;
                case 2:
                    System.out.println("삭제할 일정의 시간을 입력하세요!");

                    System.out.print("시간: ");
                    Date date = inputDate();

                    try {
                        System.out.println();
                        schedules = ScheduleUtil.removeSchedule(schedules, date);
                    } catch (Exceptions.NoSuchScheduleException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                    } catch (Exceptions.InputInterruptedException e) {
                        System.out.println(e.getMessage());
                        System.out.println();
                    }

                    break;
                case 3:
                    System.out.println("==전체 일정 목록==");

                    ScheduleUtil.printSchedules(schedules);
                    break;
            }
        }
    }

    private static Date inputDate() {
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();

        boolean validDate = false;
        Date date = new Date(year, month, day, hour, minute);
        try {
            validDate = ScheduleUtil.checkValidDate(date);
        } catch (Exceptions.IllegalDateFormatException e) {
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println("다시 입력해주세요!");
        }

        if (validDate) {
            return date;
        } else {
            return inputDate();
        }
    }
}
