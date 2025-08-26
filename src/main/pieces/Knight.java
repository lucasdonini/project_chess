package main.pieces;

import main.board.Board;
import main.board.Square;
import utils.Coordinate;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    protected Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements(final Board board) {
        List<Coordinate> aux = new ArrayList<>();
        List<String> squares = new ArrayList<>();

        aux.add(new Coordinate(row + 2, col + 1));
        aux.add(new Coordinate(row + 2, col - 1));

        aux.add(new Coordinate(row + 1, col + 2));
        aux.add(new Coordinate(row - 1, col + 2));

        aux.add(new Coordinate(row - 2, col + 1));
        aux.add(new Coordinate(row - 2, col - 1));

        aux.add(new Coordinate(row + 1, col - 2));
        aux.add(new Coordinate(row - 1, col - 2));

        aux = aux.stream().filter(SquareUtils::isValid).toList();

        for (Coordinate coord : aux) {
            String sqName = SquareUtils.getName(coord);
            Square sq = board.getSquare(sqName);

            if (sq.isFree() || sq.getPiece().isWhite != this.isWhite) {
                squares.add(sqName);
            }
        }

        return squares.stream().distinct().toList();
    }

    @Override
    protected String visualRep() {
        return "N";
    }
}
