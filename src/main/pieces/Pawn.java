package main.pieces;

import main.board.Board;
import main.board.Square;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static utils.SquareUtils.getName;

public class Pawn extends Piece {
    private final int originalRow;
    private final int dRow;

    protected Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);

        originalRow = isWhite ? 6 : 1;
        dRow = isWhite ? -1 : 1;
    }

    @Override
    public Pawn copy() {
        return new Pawn(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
        List<String> squares = new ArrayList<>();

        if (row == originalRow) {
            squares.add(getName(row + 2 * dRow, col));
        }

        if (SquareUtils.isValid(getName(row + dRow, col))) {
            squares.add(getName(row + dRow, col));
        }

        squares.sort(Comparator.naturalOrder());

        if (!board.getSquare(squares.get(0)).isFree()) {
            squares.clear();
        } else if (squares.size() > 1 && !board.getSquare(squares.get(1)).isFree()) {
            squares.remove(1);
        }

        return squares;
    }

    public List<String> getPossibleCaptureMovements(final Board board) {
        List<String> squares = new ArrayList<>();

        if (SquareUtils.isValid(row+dRow, col+1)) {
            String sqName = SquareUtils.getName(row+dRow, col+1);
            Square sq = board.getSquare(sqName);

            if (!sq.isFree() && sq.getPiece().isWhite != this.isWhite) {
                squares.add(sqName);
            }
        }

        if (SquareUtils.isValid(row+dRow, col-1)) {
            String sqName = SquareUtils.getName(row+dRow, col-1);
            Square sq = board.getSquare(sqName);

            if (!sq.isFree() && sq.getPiece().isWhite != this.isWhite) {
                squares.add(sqName);
            }
        }

        return squares;
    }

    @Override
    protected String visualRep() {
        return "P";
    }
}
