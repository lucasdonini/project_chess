package com.board;

import com.pieces.Piece;
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
        if (!SquareUtils.isValid(squareName)) {
            throw new IllegalArgumentException("Invalid square name");
        }

        Coordinate coordinate = SquareUtils.getCoordinate(squareName);
        Square square = squares[coordinate.row()][coordinate.col()];
        return square.getPiece();
    }

    // Other methods
    private void putPieces() {
        for (Square[] line : squares) {
            for (Square square : line) {
                String name = square.getName();

                if (whiteKingSquare.equals(name)) square.setPiece(Piece.whiteKing());
                else if (blackKingSquare.equals(name)) square.setPiece(Piece.blackKing());
                else if (whitePawnSquares.contains(name)) square.setPiece(Piece.whitePawn(name));
                else if (blackPawnSquares.contains(name)) square.setPiece(Piece.blackPawn(name));
                else if (whiteRookSquares.contains(name)) square.setPiece(Piece.whiteRook(name));
                else if (blackRookSquares.contains(name)) square.setPiece(Piece.blackRook(name));
            }
        }
    }
}
