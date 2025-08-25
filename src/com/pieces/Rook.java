package com.pieces;

import com.board.Square;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    protected Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements() {
        List<String> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            squares.add(SquareUtils.getName(i, col));
            squares.add(SquareUtils.getName(col, i));
        }

        return squares.stream().distinct().toList();
    }
}
