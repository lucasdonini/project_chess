package main.pieces;

import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

import static utils.SquareUtils.getName;

public class Pawn extends Piece {
    private final int originalRow;
    private final int dRow;

    protected Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);

        originalRow = row;
        dRow = isWhite ? -1 : 1;
    }

    @Override
    public List<String> getPossibleMovements() {
        List<String> squares = new ArrayList<>();

        if (row == originalRow) {
            squares.add(getName(row + 2 * dRow, col));
        }

        if (SquareUtils.isValid(getName(row + dRow, col))) {
            squares.add(getName(row + dRow, col));
        }

        return squares;
    }

    @Override
    public String toString() {
        return String.format("%cP", isWhite ? 'W' : 'B');
    }
}
