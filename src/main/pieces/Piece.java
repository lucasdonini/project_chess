package main.pieces;

import main.board.Board;
import main.game.Player;
import utils.Coordinate;
import utils.Move;
import utils.SquareUtils;
import utils.TerminalUtils;

import java.util.List;
import java.util.Map;

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
    public static Rook rook(String square, boolean isWhite, boolean wasMoved, String castlingDestination) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid square");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Rook(isWhite, position.row(), position.col(), wasMoved, castlingDestination);
    }

    private static Rook initializedRook(String square, Map<CastlingType, Move> castlings) {
        CastlingType castlingType = castlings.keySet().stream()
                .filter(t -> castlings.get(t).origin().equals(square))
                .findFirst().orElse(null);

        if (castlingType == null) {
            throw new RuntimeException("Something went wrong setting the castling destination");
        }

        String castlingDestination = castlings.get(castlingType).destination();

        return rook(square, false, false, castlingDestination);
    }

    public static Rook whiteRook(String square) {
        if (!whiteRookSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial white rook position");
        }

        return initializedRook(square, whiteKingCastling);
    }

    public static Rook blackRook(String square) {
        if (!blackRookSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black rook position");
        }

        return initializedRook(square, blackKingCastling);
    }

    // === KNIGHT ===
    public static Knight knight(String square, boolean isWhite) {
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
    public static Bishop bishop(String square, boolean isWhite) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new Bishop(isWhite, position.row(), position.col());
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
    public static Queen queen(String square, boolean isWhite) {
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
    public static King king(String square, boolean isWhite, boolean wasMoved) {
        if (!SquareUtils.isValid(square)) {
            throw new IllegalArgumentException("Invalid position");
        }

        Coordinate position = SquareUtils.getCoordinate(square);

        return new King(isWhite, position.row(), position.col(), wasMoved);
    }

    public static King whiteKing() {
        return king(whiteKingSquare, true, false);
    }

    public static King blackKing() {
        return king(blackKingSquare, false, false);
    }

    // === PAWN ===
    public static Pawn pawn(String square, boolean isWhite) {
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

        return pawn(square, true);
    }

    public static Pawn blackPawn(String square) {
        if (!blackPawnSquares.contains(square)) {
            throw new IllegalArgumentException("Invalid initial black pawn position");
        }

        return pawn(square, false);
    }

    // Getters
    public boolean isWhite() {
        return isWhite;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(row, col);
    }

    // Abstract methods
    public abstract List<String> getPossibleMovements(final Board board);
    protected abstract String visualRep();
    public abstract Piece copy();

    // Concrete methods
    public boolean belongsTo(Player player) {
        return (player == Player.WHITE && isWhite) || (player == Player.BLACK && !isWhite);
    }

    public String getSquare() {
        return SquareUtils.getName(row, col);
    }

    public void moveTo(String squareName) {
        if (!SquareUtils.isValid(squareName)) {
            throw new IllegalArgumentException("Impossible to move to required location");
        }

        Coordinate coordinate = SquareUtils.getCoordinate(squareName);
        row = coordinate.row();
        col = coordinate.col();
    }

    @Override
    public String toString() {
        String color = isWhite ? "" : TerminalUtils.RED;
        return color + visualRep() + TerminalUtils.RESET;
    }
}
