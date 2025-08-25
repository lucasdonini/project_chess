package com.pieces;

import utils.Coordinate;
import utils.SquareUtils;

import java.util.List;

public abstract class Piece {
    protected final boolean isWhite;
    protected int row;
    protected int col;

    protected Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }

    // Factory methods
    // === PAWN ===
    private static Pawn pawn(String square, boolean isWhite, int initialRow) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid position: ");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        if (position.row() != initialRow) {
            throw new IllegalArgumentException("Invalid initial pawn position");
        }

        return new Pawn(isWhite, position.row(), position.col());
    }

    public static Pawn whitePawn(String square) {
        return pawn(square, true, 6);
    }

    public static Pawn blackPawn(String square) {
        return pawn(square, false, 1);
    }

    // === KING ===
    private static King king(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid position");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new King(isWhite, position.row(), position.col());
    }

    public static King whiteKing() {
        return king("e1", true);
    }

    public static King blackKing() {
        return king("e8", false);
    }

    public boolean isWhitePiece() {
        return isWhite;
    }

    public abstract List<String> getPossibleMovements();
}
