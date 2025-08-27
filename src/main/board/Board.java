package main.board;

import main.game.Player;
import main.pieces.Piece;
import utils.Coordinate;
import utils.SquareUtils;

import java.util.*;

import static utils.PiecePositioningUtils.*;

public class Board {
    private final Square[][] squares;

    // Constructors
    public Board() {
        squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(SquareUtils.getName(i, j));
            }
        }
    }

    private Board(Square[][] squares) {
        this.squares = squares;
    }

    // Factory methods
    public static Board setBoard() {
        Board board = new Board();
        board.putPieces();

        return board;
    }

    public Board copy() {
        Square[][] cpSquares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cpSquares[i][j] = squares[i][j].copy();
            }
        }

        return new Board(cpSquares);
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

                // Rooks
                if (whiteRookSquares.contains(squareName)) piece = Piece.whiteRook(squareName);
                else if (blackRookSquares.contains(squareName)) piece = Piece.blackRook(squareName);

                // Knights
                else if (whiteKnightSquares.contains(squareName)) piece = Piece.whiteKnight(squareName);
                else if (blackKnightSquares.contains(squareName)) piece = Piece.blackKnight(squareName);

                // Bishops
                else if (whiteBishopSquares.contains(squareName)) piece = Piece.whiteBishop(squareName);
                else if (blackBishopSquares.contains(squareName)) piece = Piece.blackBishop(squareName);

                // Queens
                else if (whiteQueenSquare.equals(squareName)) piece = Piece.whiteQueen();
                else if (blackQueenSquare.equals(squareName)) piece = Piece.blackQueen();

                // Kings
                else if (whiteKingSquare.equals(squareName)) piece = Piece.whiteKing();
                else if (blackKingSquare.equals(squareName)) piece = Piece.blackKing();

                // Pawns
                else if (whitePawnSquares.contains(squareName)) piece = Piece.whitePawn(squareName);
                else if (blackPawnSquares.contains(squareName)) piece = Piece.blackPawn(squareName);

                else piece = null;

                square.setPiece(piece);
            }
        }
    }

    public Map<Player, List<Piece>> getPiecesPerPlayer() {
        Map<Player, List<Piece>> map = Map.of(Player.WHITE, new ArrayList<>(), Player.BLACK, new ArrayList<>());

        for (Square[] line : squares) {
            for (Square sq : line) {
                Piece piece = sq.getPiece();

                if (piece == null) continue;

                Player player = piece.isWhite() ? Player.WHITE : Player.BLACK;
                map.get(player).add(piece);
            }
        }

        return map;
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
                    sb.append(" - ");
                    continue;
                }

                String text = String.format(" %3s ", p);
                sb.append(text);
            }
            sb.append('\n');
        }

        sb.append("\n ");
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('a' + i);
            sb.append("  ");
            sb.append(letter);
        }

        return sb.toString();
    }
}
