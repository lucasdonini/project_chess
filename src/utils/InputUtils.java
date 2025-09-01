package utils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

import static utils.TerminalUtils.inRed;

public class InputUtils {
    private static final Scanner sc = new Scanner(System.in);

    @SafeVarargs
    public static String input(String text, String errMsg, Predicate<String>... validations) {
        String s;

        while (true) {
            System.out.print(text);

            s = sc.nextLine().trim();

            final String finalS = s;
            if (Arrays.stream(validations).allMatch(v -> v.test(finalS))) {
                return s;
            }

            System.out.println(inRed("\nError: " + errMsg));
        }
    }

    public static String input(String text, String errMsg) {
        return input(text, errMsg, _ -> true);
    }

    @SafeVarargs
    public static String input(String text, Predicate<String>... validations) {
        return input(text, "Invalid entry", validations);
    }

    public static String input(String text) {
        return input(text, _ -> true);
    }
}
