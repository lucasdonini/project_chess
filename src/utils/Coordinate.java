package utils;

public record Coordinate(int row, int col) {
    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }

    public Coordinate copy() {
        return new Coordinate(row, col);
    }
}
