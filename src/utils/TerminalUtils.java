package utils;

public class TerminalUtils {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static String inRed(String text) {
        return RED + text + RESET;
    }
}
