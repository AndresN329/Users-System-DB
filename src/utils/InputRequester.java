package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

import javax.swing.JOptionPane;

public class InputRequester {
    private final static String DEFAULT_INVALID_INPUT_MESSAGE = "Invalid input. Please try again.";
    private final static String DEFAULT_INVALID_INPUT_PANE_TITLE = "Invalid Input";

    // ------------------ STRING ------------------
    public static String requestString(String prompt) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, false);
    }

    public static String requestString(String prompt, boolean allowEmpty) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, allowEmpty);
    }

    public static String requestString(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputString = JOptionPane.showInputDialog(null, prompt);
            String trimmedInput = inputString == null ? "" : inputString.trim();
            if (allowEmpty)
                return trimmedInput;

            if (!trimmedInput.isEmpty())
                return trimmedInput;

            JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // ------------------ DATE ------------------
    public static Date requestDate(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message);
                if (input == null) { // Cancel pressed
                    return null;
                }

                LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Invalid format. Use yyyy-MM-dd.", DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, boolean allowEmpty) {
        return requestLocalDate(prompt, "Invalid date format. Use yyyy-MM-dd", allowEmpty);
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, String invalidInputMessage) {
        return requestLocalDate(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, String invalidInputMessage, boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String inputLocalDate = requestString(prompt + "\nFormat: yyyy-MM-dd", allowEmpty);
            if (allowEmpty && inputLocalDate.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalDate.parse(inputLocalDate, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ LOCALDATETIME ------------------
    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, String invalidInputMessage) {
        return requestLocalDateTime(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, boolean allowEmpty) {
        return requestLocalDateTime(prompt, "Invalid date and time format. Use yyyy-MM-ddTHH:mm:ss", allowEmpty);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt) {
        return requestLocalDateTime(prompt, "Invalid date and time format. Use yyyy-MM-ddTHH:mm:ss", false);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, String invalidInputMessage,
                                                               boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        while (true) {
            String inputLocalDateTime = requestString(prompt + "\nFormat: yyyy-MM-ddTHH:mm:ss", allowEmpty);
            if (allowEmpty && inputLocalDateTime.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalDateTime.parse(inputLocalDateTime, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ LOCALTIME ------------------
    public static Optional<LocalTime> requestLocalTime(String prompt, String invalidInputMessage) {
        return requestLocalTime(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt, boolean allowEmpty) {
        return requestLocalTime(prompt, "Invalid time format. Use HH:mm or HH:mm:ss", allowEmpty);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt) {
        return requestLocalTime(prompt, "Invalid time format. Use HH:mm or HH:mm:ss", false);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt, String invalidInputMessage, boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm[:ss]");
        while (true) {
            String inputLocalTime = requestString(prompt + "\nFormat: HH:mm or HH:mm:ss", allowEmpty);
            if (allowEmpty && inputLocalTime.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalTime.parse(inputLocalTime, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ INTEGER ------------------
    public static int requestInteger(String prompt, String invalidInputMessage, boolean allowEmpty, int defaultValue) {
        while (true) {
            String inputInteger = requestString(prompt, allowEmpty);
            if (allowEmpty && (inputInteger == null || inputInteger.isEmpty())) {
                return defaultValue;
            }

            try {
                return Integer.parseInt(inputInteger);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static int requestInteger(String prompt, String invalidInputMessage, int defaultValue) {
        return requestInteger(prompt, invalidInputMessage, false, defaultValue);
    }

    public static int requestInteger(String prompt, int defaultValue) {
        return requestInteger(prompt, "The input is not an integer. Please try again.", false, defaultValue);
    }

    public static int requestInteger(String prompt) {
        return requestInteger(prompt, "The input is not an integer. Please try again.", false, 0);
    }

    // ------------------ DOUBLE ------------------
    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage) {
        return requestDouble(prompt, invalidInputMessage, false);
    }

    public static Optional<Double> requestDouble(String prompt, boolean allowEmpty) {
        return requestDouble(prompt, "The input is not a valid number. Please try again.", allowEmpty);
    }

    public static Optional<Double> requestDouble(String prompt) {
        return requestDouble(prompt, "The input is not a valid number. Please try again.", false);
    }

    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputDouble = requestString(prompt, allowEmpty);
            if (allowEmpty && inputDouble.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(Double.parseDouble(inputDouble));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}