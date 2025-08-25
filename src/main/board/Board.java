package main.board;

import main.pieces.Piece;
import utils.Coordinate;
import utils.SquareUtils;

import static utils.PiecePositioningUtils.*;

public class Board {
    private final Square[][] squares = new Square[8][8];

    // Constructors
    private Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(SquareUtils.getName(i, j));
            }
        }
    }

    // Factory methods
    public static Board setBoard() {
        Board board = new Board();
        board.putPieces();

        return board;
    }

    // Getters
    public Piece getPiece(String squareName) {
        return getSquare(squareName).getPiece();
    }

    public Square getSquare(String squareName) {
        if (!SquareUtils.isValid(squareName)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        Coordinate coordinate = SquareUtils.getCoordinate(squareName);
        return squares[coordinate.row()][coordinate.col()];
    }

    // Other methods
    private void putPieces() {
        for (Square[] line : squares) {
            for (Square square : line) {
                String squareName = square.getName();
                Piece piece;

                if (whiteRookSquares.contains(squareName)) piece = Piece.whiteRook(squareName);
                else if (blackRookSquares.contains(squareName)) piece = Piece.blackRook(squareName);
                else if (whiteKnightSquares.contains(squareName)) piece = Piece.whiteKnight(squareName);
                else if (blackKnightSquares.contains(squareName)) piece = Piece.blackKnight(squareName);
                else if (whiteBishopSquares.contains(squareName)) piece = Piece.whiteBishop(squareName);
                else if (blackBishopSquares.contains(squareName)) piece = Piece.blackBishop(squareName);
                else if (whiteQueenSquare.equals(squareName)) piece = Piece.whiteQueen();
                else if (blackQueenSquare.equals(squareName)) piece = Piece.blackQueen();
                else if (whiteKingSquare.equals(squareName)) piece = Piece.whiteKing();
                else if (blackKingSquare.equals(squareName)) piece = Piece.blackKing();
                else if (whitePawnSquares.contains(squareName)) piece = Piece.whitePawn(squareName);
                else if (blackPawnSquares.contains(squareName)) piece = Piece.blackPawn(squareName);
                else piece = null;

                square.setPiece(piece);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 8;

        for (Square[] row : squares) {
            sb.append(count);
            sb.append(" ");
            count--;

            for (Square sq : row) {
                Piece p = sq.getPiece();

                if (p == null) {
                    sb.append("   - ");
                    continue;
                }

                String text = String.format(" %3s ", p);
                sb.append(text);
            }
            sb.append('\n');
        }

        sb.append("\n ");
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('A' + i);
            sb.append("    ");
            sb.append(letter);
        }

        return sb.toString();
    }
}
