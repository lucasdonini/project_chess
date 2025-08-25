package com.board;

import com.pieces.Piece;
import utils.Coordinate;
import utils.SquareUtils;

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

                switch (name) {
                    case "e1" -> square.setPiece(Piece.whiteKing());
                    case "e8" -> square.setPiece(Piece.blackKing());
                    case "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2" -> square.setPiece(Piece.whitePawn(name));
                    case "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7" -> square.setPiece(Piece.blackPawn(name));
                }
            }
        }
    }
}
