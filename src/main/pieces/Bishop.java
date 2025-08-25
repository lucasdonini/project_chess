package main.pieces;

import utils.Coordinate;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    protected Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements() {
        List<String> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<Coordinate> inner = List.of(
                    new Coordinate(row + i, col + i),
                    new Coordinate(row + i, col - i),
                    new Coordinate(row - i, col + i),
                    new Coordinate(row - i, col - i)
            );

            inner = inner.stream().distinct().filter(SquareUtils::isValid).toList();
            squares.addAll(inner.stream().map(SquareUtils::getName).toList());
        }

        return squares;
    }

    @Override
    public String toString() {
        return String.format("%cB", isWhite ? 'W' : 'B');
    }
}
