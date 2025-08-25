package com.pieces;

import utils.Coordinate;
import utils.SquareUtils;

import static utils.PiecePositioningUtils.*;

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

        return new Pawn(isWhite, position.row(), position.col());
    }

    public static Pawn whitePawn(String square) {
        if (!whitePawnSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial white pawn position");
        }

        return pawn(square, true, whitePawnInitialRow);
    }

    public static Pawn blackPawn(String square) {
        if (!blackPawnSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black pawn position");
        }

        return pawn(square, false, blackPawnInitialRow);
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
        return king(whiteKingSquare, true);
    }

    public static King blackKing() {
        return king(blackKingSquare, false);
    }

    // === ROOK ===
    private static Rook rook(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid square");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Rook(isWhite, position.row(), position.col());
    }

    public static Rook whiteRook(String square) {
        if (!whiteRookSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial white rook position");
        }

        return rook(square, true);
    }

    public static Rook blackRook(String square) {
        if (!blackRookSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black rook position");
        }

        return rook(square, false);
    }

    // === BISHOP ===
    private static Bishop bishop(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Bishop(true, position.row(), position.col());
    }

    public static Bishop whiteBishop(String square) {
        if (!whiteBishopSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial white bishop square");
        }

        return bishop(square, true);
    }

    public static Bishop blackBishop(String square) {
        if (!blackBishopSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black bishop square");
        }

        return bishop(square, false);
    }

    // Other methods
    public boolean isWhitePiece() {
        return isWhite;
    }

    public abstract List<String> getPossibleMovements();
}
