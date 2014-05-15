package ajou.cse.oop.models;

public class Exceptions {

    public static class SameScheduleExistsException extends Exception {
        public SameScheduleExistsException(String s) {
            super(s);
        }
    }

    public static class NoSuchScheduleException extends Exception {
        public NoSuchScheduleException(String s) {
            super(s);
        }
    }

    public static class InputInterruptedException extends Exception {
        public InputInterruptedException(String s) {
            super(s);
        }
    }

    public static class IllegalDateFormatException extends Exception {
        public IllegalDateFormatException(String s) {
            super(s);
        }
    }

}
