package ajou.cse.oop.utils;

import ajou.cse.oop.models.Exceptions.*;
import ajou.cse.oop.models.Date;
import ajou.cse.oop.models.Schedule;

import java.util.Scanner;

public class ScheduleUtil {
    public static void sort(Schedule[] schedules) {
        for (int i = 0 ; i < schedules.length ; i++) {
            int min = i;

            for (int j = i; j < schedules.length ; j++) {

                Schedule schedule = schedules[min];
                Schedule target = schedules[j];

                if (schedule.getStartDate().compareTo(target.getStartDate())
                        == Date.AFTER_THAN_COMPARE_DATE) {
                    min = j;
                }
            }

            Schedule tmp = schedules[i];
            schedules[i] = schedules[min];
            schedules[min] = tmp;
        }

        printSchedules(schedules);
    }

    public static Schedule[] addSchedule(Schedule[] schedules, Schedule target)
            throws SameScheduleExistsException, IllegalDateFormatException {
        if (includedSchedule(schedules, target)) {
            throw new SameScheduleExistsException("겹치는 일정이 존재합니다!");
        } else if (!isValidSchedule(target)) {
            throw new IllegalDateFormatException("올바르지 않은 날짜 형식입니다!");
        }

        Schedule[] newSchedules = new Schedule[schedules.length + 1];
        for (int i = 0 ; i < schedules.length ; i++) {
            newSchedules[i] = schedules[i];
        }

        newSchedules[schedules.length] = target;

        sort(newSchedules);

        return newSchedules;
    }

    public static Schedule[] removeSchedule(Schedule[] schedules, Date date)
            throws NoSuchScheduleException, InputInterruptedException {
        Schedule remove = null;
        for (Schedule schedule : schedules) {
            remove = includedDate(schedule, date) ? schedule : null;
        }

        if (remove == null) {
            throw new NoSuchScheduleException("해당 시간에 일정이 없습니다!");
        }

        printSchedules(new Schedule[]{remove});

        System.out.print("이 일정을 삭제하시겠습니까? (y/n) : ");

        Scanner scanner = new Scanner(System.in);
        String ans = scanner.next();

        if (ans.equals("y")) {
            Schedule[] newSchedules = new Schedule[schedules.length - 1];
            for (int i = 0 ; i < schedules.length ; i++) {
                if (!remove.equals(schedules[i])) {
                    newSchedules[i] = schedules[i];
                }
            }

            return newSchedules;
        } else if (ans.equals("n")) {
            throw new InputInterruptedException("취소되었습니다.");
        } else {
            throw new InputInterruptedException("다시 시도하세요.");
        }
    }

    public static boolean isValidSchedule(Schedule target) {
        return target.getEndDate().compareTo(target.getStartDate()) == Date.AFTER_THAN_COMPARE_DATE
                && target.getStartDate().isSameDayWith(target.getEndDate());
    }

    public static boolean checkValidDate(Date date) throws IllegalDateFormatException {
        String message;

        if (date.getYear() <= 0) {
            message = "연도는 자연수만 입력 가능합니다!";
        } else if (date.getMonth() <= 0 || date.getMonth() > 12) {
            message = "1 ~ 12월까지의 날짜만 입력해주세요!";
        } else if (date.getDay() <= 0 || date.getDay() > 31) {
            message = "1 ~ 31일까지의 날짜만 입력해주세요!";
        } else if (date.getHour() < 0 || date.getHour() > 23) {
            message = "0 ~ 23시까지의 시간만 입력해주세요!";
        } else if (date.getMinute() < 0 || (date.getMinute() % 30 != 0)) {
            message = "30분 단위로 입력해주세요!";
        } else {
            return true;
        }

        throw new IllegalDateFormatException(message);
    }

    public static boolean includedSchedule(Schedule[] schedules, Schedule target) {
        for (Schedule schedule : schedules) {
            if ((schedule.getStartDate().compareTo(target.getStartDate()) == Date.BEFORE_THAN_COMPARE_DATE
                    && schedule.getEndDate().compareTo(target.getStartDate()) == Date.AFTER_THAN_COMPARE_DATE)
                    || (schedule.getStartDate().compareTo(target.getEndDate()) == Date.BEFORE_THAN_COMPARE_DATE
                    && schedule.getEndDate().compareTo(target.getEndDate()) == Date.AFTER_THAN_COMPARE_DATE)) {
                return true;
            }
        }

        return false;
    }

    public static boolean includedDate(Schedule schedule, Date date) {
        return schedule.getStartDate().compareTo(date) == Date.BEFORE_THAN_COMPARE_DATE
                && schedule.getEndDate().compareTo(date) == Date.AFTER_THAN_COMPARE_DATE;
    }

    public static void printSchedules(Schedule[] schedules) {
        for (Schedule schedule : schedules) {
            System.out.println(schedule.toString());
            System.out.println();
        }
    }
}
