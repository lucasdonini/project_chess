package main.pieces;

import main.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    protected Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public Queen copy() {
        return new Queen(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
        List<String> squares = new ArrayList<>();

        squares.addAll(bishop(getSquare(), isWhite).getPossibleMovements(board));
        squares.addAll(rook(getSquare(), isWhite).getPossibleMovements(board));

        return squares.stream().distinct().toList();
    }

    @Override
    protected String visualRep() {
        return "Q";
    }
}
