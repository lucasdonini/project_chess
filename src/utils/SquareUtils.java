package utils;

import java.util.ArrayList;
import java.util.List;

public class SquareUtils {
    public static String getName(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        return String.format("%c%d", 'a' + col, 8 - row);
    }

    public static String getName(Coordinate coordinate) {
        return getName(coordinate.row(), coordinate.col());
    }

    public static Coordinate getCoordinate(String name) {
        if (!isValid(name)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        char rowChr = name.charAt(1);
        char col = Character.toLowerCase(name.charAt(0));

        int row = Integer.parseInt(String.valueOf(rowChr));
        row = 8 - row;

        return new Coordinate(row, col - 'a');
    }

    public static boolean isValid(String name) {
        if (name.length() != 2) return false;

        try {
            name = name.toLowerCase();

            int row = Integer.parseInt(String.valueOf(name.charAt(1))) - 1;
            int col = name.charAt(0) - 'a';

            return row >= 0 && row <= 7 && col >= 0 && col <= 7;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValid(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

    public static boolean isValid(Coordinate coordinate) {
        return isValid(coordinate.row(), coordinate.col());
    }

    public static List<String> getAllSquares() {
        List<String> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares.add(getName(i, j));
            }
        }

        return squares;
    }
}
