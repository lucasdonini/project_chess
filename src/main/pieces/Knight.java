package main.pieces;

import utils.Coordinate;
import utils.SquareUtils;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    protected Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public List<String> getPossibleMovements() {
        List<Coordinate> aux = new ArrayList<>();

        aux.add(new Coordinate(row + 2, col + 1));
        aux.add(new Coordinate(row + 2, col - 1));

        aux.add(new Coordinate(row + 1, col + 2));
        aux.add(new Coordinate(row - 1, col + 2));

        aux.add(new Coordinate(row - 2, col + 1));
        aux.add(new Coordinate(row - 2, col - 1));

        aux.add(new Coordinate(row + 1, col - 2));
        aux.add(new Coordinate(row - 1, col - 2));

        aux = aux.stream().filter(SquareUtils::isValid).toList();
        List<String> squares = new ArrayList<>(aux.stream().map(SquareUtils::getName).toList());

        return squares.stream().distinct().toList();
    }

    @Override
    public String toString() {
        return String.format("%cN", isWhite ? 'W' : 'B');
    }
}
