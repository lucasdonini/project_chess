package main.board;

import main.pieces.Piece;
import utils.Coordinate;
import utils.SquareUtils;

public class Square {
    private final Coordinate coordinate;
    private Piece piece;

    // Constructors
    public Square(String name, Piece piece) {
        if (!SquareUtils.isValid(name)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        this.coordinate = SquareUtils.getCoordinate(name);
        this.piece = piece;
    }

    public Square(String name) {
        this(name, null);
    }

    protected Square(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    // Factory methods
    public Square copy() {
        Piece newPiece = piece == null ? null : piece.copy();
        return new Square(coordinate.copy(), newPiece);
    }

    // Getters
    public Piece getPiece() {
        return piece;
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
}
