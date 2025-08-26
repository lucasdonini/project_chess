package main.pieces;

import main.board.Board;
import main.board.Square;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    protected Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
        List<String> squares = new ArrayList<>();

        // Auxiliar variables
        boolean up = true, down = true, left = true, right = true;
        int count = 1;

        while (up || down || left || right) {
            String sqName;
            Square sq;

            up = up && SquareUtils.isValid(row - count, col);
            down = down && SquareUtils.isValid(row + count, col);
            left = left && SquareUtils.isValid(row, col - count);
            right = right && SquareUtils.isValid(row, col + count);

            if (up) {
                sqName = SquareUtils.getName(row - count, col);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    up = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (down) {
                sqName = SquareUtils.getName(row + count, col);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    down = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (left) {
                sqName = SquareUtils.getName(row, col - count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    left = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (right) {
                sqName = SquareUtils.getName(row, col + count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    right = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            count++;
        }

        return squares;
    }

    @Override
    public String toString() {
        return String.format("%cR", isWhite ? 'W' : 'B');
    }
}
