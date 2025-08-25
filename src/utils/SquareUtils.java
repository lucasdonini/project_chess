package utils;

public class SquareUtils {
    protected static final char[] letters = "ABCDEFGH".toCharArray();

    public static String getSquareName(int row, int col) {
        if (row < 0 || col < 0 || row > 7 || col > 7) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        return String.format("%c%d", letters[col], 8-row);
    }

    public static Coordinate getCoordinate(String name) {
        if (!isValid(name)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        char row = name.charAt(1);
        char col = Character.toUpperCase(name.charAt(0));

        return new Coordinate(8 - (row - '0'), col - 'A');
    }

    public static boolean isValid(String name) {
        if (name.length() != 2) return false;

        try {
            name = name.toUpperCase();

            int row = Integer.parseInt(String.valueOf(name.charAt(1))) - 1;
            int col = name.charAt(0) - 'A';

            return row >= 0 && row <= 7 && col >= 0 && col <= 7;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
