package com.pieces;

import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

import static utils.SquareUtils.getSquareName;

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
            squares.add(getSquareName(row + 2 * dRow, col));
        }

        if (SquareUtils.isValid(getSquareName(row + dRow, col))) {
            squares.add(getSquareName(row + dRow, col));
        }

        return squares;
    }
}
