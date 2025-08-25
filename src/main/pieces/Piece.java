package main.pieces;

import main.game.Player;
import utils.Coordinate;
import utils.SquareUtils;

import java.util.List;

import static utils.PiecePositioningUtils.*;

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
    // === ROOK ===
    protected static Rook rook(String square, boolean isWhite) {
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

    // === KNIGHT ===
    protected static Knight knight(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid square");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Knight(isWhite, position.row(), position.col());
    }

    public static Knight whiteKnight(String square) {
        if (!whiteKnightSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial white knight square");
        }

        return knight(square, true);
    }

    public static Knight blackKnight(String square) {
        if (!blackKnightSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black knight square");
        }

        return knight(square, false);
    }

    // === BISHOP ===
    protected static Bishop bishop(String square, boolean isWhite) {
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

    // === QUEEN ===
    protected static Queen queen(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid position");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Queen(isWhite, position.row(), position.col());
    }

    public static Queen whiteQueen() {
        return queen(whiteQueenSquare, true);
    }

    public static Queen blackQueen() {
        return queen(blackQueenSquare, false);
    }

    // === KING ===
    protected static King king(String square, boolean isWhite) {
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

    // === PAWN ===
    protected static Pawn pawn(String square, boolean isWhite, int initialRow) {
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

    // Getters
    public boolean isWhite() {
        return isWhite;
    }

    // Other methods
    public boolean belongsTo(Player player) {
        return (player == Player.WHITE && isWhite) || (player == Player.BLACK && !isWhite);
    }

    public String getSquare() {
        return SquareUtils.getName(row, col);
    }

    public abstract List<String> getPossibleMovements();
}
