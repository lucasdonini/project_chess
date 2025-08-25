package com.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    protected Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements() {
        List<String> squares = new ArrayList<>();

        squares.addAll(bishop(getSquare(), isWhite).getPossibleMovements());
        squares.addAll(rook(getSquare(), isWhite).getPossibleMovements());

        return squares.stream().distinct().toList();
    }

    @Override
    public String toString() {
        return String.format("%cQ", isWhite ? 'W' : 'B');
    }
}
