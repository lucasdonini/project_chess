package utils;

public class SquareUtils {
    public static String getName(int row, int col) {
        if (row < 0 || col < 0 || row > 7 || col > 7) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        return String.format("%c%d", 'a' + col, 8-row);
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
}
