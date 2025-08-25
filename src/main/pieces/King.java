package main.pieces;

import java.util.ArrayList;
import java.util.List;

import static utils.SquareUtils.getName;

public class King extends Piece {
    protected King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements() {
        List<Integer> possibleFinalRows = new ArrayList<>();
        List<Integer> possibleFinalColumns = new ArrayList<>();
        List<String> possibleFinalSqares = new ArrayList<>();

        possibleFinalRows.add(row);
        possibleFinalRows.add(row + 1);
        possibleFinalRows.add(row - 1);

        possibleFinalColumns.add(col);
        possibleFinalColumns.add(col + 1);
        possibleFinalColumns.add(col - 1);

        for (int fRow : possibleFinalRows) {
            for (int fCol : possibleFinalColumns) {
                if (fRow < 0 || fCol < 0 || fRow > 7 || fCol > 7) {
                    continue;
                }

                String square = getName(fRow, fCol);
                possibleFinalSqares.add(square);
            }
        }

        return possibleFinalSqares;
    }

    @Override
    public String toString() {
        return String.format("%cK", isWhite ? 'W' : 'B');
    }
}
