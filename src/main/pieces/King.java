package main.pieces;

import main.board.Board;
import main.board.Square;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    protected King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
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
                if (!SquareUtils.isValid(fRow, fCol)) continue;

                String sqName = SquareUtils.getName(fRow, fCol);
                Square sq = board.getSquare(sqName);

                if (sq.isFree() || sq.getPiece().isWhite != this.isWhite) {
                    possibleFinalSqares.add(sqName);
                }
            }
        }

        return possibleFinalSqares;
    }

    @Override
    public String toString() {
        return String.format("%cK", isWhite ? 'W' : 'B');
    }
}
