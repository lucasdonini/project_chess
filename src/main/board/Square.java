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

    public boolean hasWhitePiece() {
        return piece != null && piece.isWhite();
    }

    public boolean hasBlackPiece() {
        return piece != null && !piece.isWhite();
    }

    public boolean hasKing() {
        return piece != null && "King".equals(piece.getClass().getName());
    }

    public boolean hasWhiteKing() {
        return hasWhitePiece() && hasKing();
    }

    public boolean hasBlackKing() {
        return hasBlackPiece() && hasKing();
    }
}
