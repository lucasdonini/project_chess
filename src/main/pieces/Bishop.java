package main.pieces;

import main.board.Board;
import main.board.Square;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    protected Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
        List<String> squares = new ArrayList<>();

        // Auxiliar variables
        boolean ur = true, ul = true, dr = true, dl = true;
        int count = 1;

        while (ur || ul || dr || dl) {
            String sqName;
            Square sq;

            ur = ur && SquareUtils.isValid(row - count, col + count); // Up Right
            ul = ul && SquareUtils.isValid(row - count, col - count); // Up Left
            dr = dr && SquareUtils.isValid(row + count, col + count); // Down Right
            dl = dl && SquareUtils.isValid(row + count, col - count); // Down Left

            if (ur) {
                sqName = SquareUtils.getName(row - count, col + count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    ur = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (ul) {
                sqName = SquareUtils.getName(row - count, col - count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    ul = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (dr) {
                sqName = SquareUtils.getName(row + count, col + count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    dr = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            if (dl) {
                sqName = SquareUtils.getName(row + count, col - count);
                sq = board.getSquare(sqName);

                if (sq.isFree()) {
                    squares.add(sqName);
                } else {
                    dl = false;
                    if (sq.getPiece().isWhite != this.isWhite) squares.add(sqName);
                }
            }

            count++;
        }

        return squares;
    }

    @Override
    public String toString() {
        return String.format("%cB", isWhite ? 'W' : 'B');
    }
}
