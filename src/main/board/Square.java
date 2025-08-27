package main.board;

import main.pieces.Piece;
import utils.Coordinate;
import utils.SquareUtils;

public class Square {
    private final Coordinate coordinate;
    private final boolean isWhite;
    private Piece piece;

    // Constructors
    public Square(String name, Piece piece, boolean isWhite) {
        if (!SquareUtils.isValid(name)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        this.coordinate = SquareUtils.getCoordinate(name);
        this.isWhite = isWhite;
        this.piece = piece;
    }

    public Square(String name, boolean isWhite) {
        this(name, null, isWhite);
    }
    protected Square(Coordinate coordinate, Piece piece, boolean isWhite) {
        this.coordinate = coordinate;
        this.piece = piece;
        this.isWhite = isWhite;
    }

    // Factory methods
    public Square copy() {
        Piece newPiece = piece == null ? null : piece.copy();
        return new Square(coordinate.copy(), newPiece, isWhite);
    }

    // Getters
    public Piece getPiece() {
        return piece;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    // Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Other Methods
    public String getName() {
        return SquareUtils.getName(coordinate);
    }

    public boolean isFree() {
        return piece == null;
    }

    @Override
    public String toString() {
        if (piece == null) {
            return isWhite ? "■" : "□";
        }

        return piece.toString();
    }
}
