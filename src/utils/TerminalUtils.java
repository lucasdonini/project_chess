package utils;

import java.util.Objects;

public class TerminalUtils {
    public static final String RED = "\u001B[1;31m";
    public static final String GREEN = "\u001B[1;32m";
    public static final String RESET = "\u001B[0m";

    public static String colored(String text, String ANSIColor, Object... args) {
        String coloredText = ANSIColor + text + RESET;
        return String.format(coloredText, args);
    }

    public static String inRed(String text, Object... args) {
        return colored(text, RED, args);
    }

    public static String inGreen(String text, Object... args) {
        return colored(text, GREEN, args);
    }
}
